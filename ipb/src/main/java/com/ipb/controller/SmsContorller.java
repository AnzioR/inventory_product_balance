package com.ipb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ipb.domain.Message;
import com.ipb.domain.SmsResponse;
import com.ipb.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
@RestController
@RequestMapping("/api/v1/sms")
public class SmsContorller {

    @Autowired
    SmsService smsService;

    @PostMapping("/send")
    public SmsResponse sendSms(@RequestBody Message message) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
      SmsResponse response = smsService.sendSms(message);
      return response;
    }
  }
