//package com.ipb.controller;
//
//import com.ipb.domain.Staff;
//import com.ipb.service.StaffService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor

//public class LoginController {
//  @Autowired
// StaffService staffService;
//
//
//  @GetMapping("/login")
//  public String staffLogin(Model model){
//    Staff staff = new Staff();
//    model.addAttribute("staff", staff);
//    return "/staff/login";
//  }
////  @PostMapping("/login")
////  public String StaffLogin(Model model);
//
//  @GetMapping("/one")
//  public Staff staff(Long id)
//  {
//    try {
//      Staff staff = staffService.get(id);
//      return staff;
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//
//  }
//
