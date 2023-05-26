//package com.ipb.service;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WeatherAuto {
//
//    @Scheduled(fixedDelay = 60 * 60* 1000)
//    public void weatherAuto() {
//
//        // Jsoup를 이용해서 http://www.cgv.co.kr/movies/ 크롤링
//        String url = "https://www.google.com/search?q=%EC%9D%BC%EA%B8%B0%EC%98%88%EB%B3%B4&rlz=1C1CHZN_koKR1051KR1051&oq=%EC%9D%BC%EA%B8%B0%EC%98%88%EB%B3%B4&aqs=chrome..69i57j0i402i512j0i131i433i512j0i512l7.1756j1j15&sourceid=chrome&ie=UTF-8"; //크롤링할 url지정
//        Document doc = null;        //Document에는 페이지의 전체 소스가 저장된다
//
//        try {
//            doc = Jsoup.connect(url).get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
//        Elements element = doc.select("div#wob_dp.wob_dfc");
//
//        System.out.println("============================================================");
//
//        //Iterator을 사용하여 하나씩 값 가져오기
//        Iterator<Element> ie1 = element.select("strong.rank").iterator();
//        Iterator<Element> ie2 = element.select("strong.title").iterator();
//
//        while (ie1.hasNext()) {
//            System.out.println(ie1.next().text()+"\t"+ie2.next().text());
//        }
//
//        System.out.println("============================================================");
//    }
//}
