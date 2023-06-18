package com.ipb.controller;

import com.ipb.domain.StoreProduct;
import com.ipb.domain.StoreProductIssue;
import com.ipb.service.StoreProductIssueService;
import com.ipb.service.StoreProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static java.time.LocalTime.now;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
@Api(tags = {"이슈"})

public class StoreProductIssueController {

  @Autowired
  StoreProductService storeProductService;

  @Autowired
  StoreProductIssueService storeProductIssueService;
}
