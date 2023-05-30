package com.ipb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipb.domain.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;


@Slf4j
@Service
@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class SmsService {


    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;

    // Signature 필드 값 생성
    public String makeSignature(String time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
      String space = " ";					// one space
      String newLine = "\n";					// new line
      String method = "POST";					// method
      String url = "/sms/v2/services/" + this.serviceId + "/messages";	// url (include query string)
      String accessKey = this.accessKey;			// access key id (from portal or Sub Account)
      String secretKey = this.secretKey;

      String message = new StringBuilder()
          .append(method)
          .append(space)
          .append(url)
          .append(newLine)
          .append(time)
          .append(newLine)
          .append(accessKey)
          .toString();

      SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
      Mac mac = Mac.getInstance("HmacSHA256");
      mac.init(signingKey);

      byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
      String encodeBase64String = Base64.encodeBase64String(rawHmac);

      return encodeBase64String;
    }

    public SmsResponse sendSms(Message message) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
      String time = Long.toString(System.currentTimeMillis());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("x-ncp-apigw-timestamp", time);
      headers.set("x-ncp-iam-access-key", accessKey);
      headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

      List<Message> messages = new ArrayList<>();
      messages.add(message); // API 문서를 봐야알겠지만 이게 왜 배열로 담기는지 ..?이렇게 가져오라고 되어있어서...........사이트 공유해주세요

      SmsRequest request = SmsRequest.builder()
          .type("SMS")
          .contentType("COMM")
          .countryCode("82")
          .from(phone)
          .content(message.getContent())
          .messages(messages)
          .build();

      // 쌓은 바디를 json 형태로 반환
      ObjectMapper objectMapper = new ObjectMapper();
      String body = objectMapper.writeValueAsString(request);
      // jsonBody와 헤더 조립
      HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

      RestTemplate restTemplate = new RestTemplate();
      restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
      // restTemplate로 post 요청 보내고 오류가 없으면 202 코드 반환
      SmsResponse response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, SmsResponse.class);

      return response;
    }
}

