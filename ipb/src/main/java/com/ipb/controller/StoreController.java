package com.ipb.controller;

import com.ipb.domain.Store;
import com.ipb.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {

  @Autowired
  StoreService storeService;

  @GetMapping("/storelist")
  public String get(Model model) {
    List<Store> list = null;
    try {
      list = storeService.get();
      model.addAttribute("slist", list);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("");
    return "main";
  }
}
