package com.ipb.service;


import com.ipb.domain.ProductInfo;
import com.ipb.frame.MyService;
import com.ipb.mapper.ProductInfoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInfoService implements MyService<Long, ProductInfo> {

  @Autowired
  ProductInfoMapper productInfoMapper;

  @Override
  public void register(ProductInfo productInfo) throws Exception {
    productInfoMapper.insert(productInfo);

  }

  @Override
  public void modify(ProductInfo productInfo) throws Exception {
    productInfoMapper.update(productInfo);
  }

  @Override
  public void remove(Long product_code) throws Exception {
    productInfoMapper.delete(product_code);
  }

  @Override
  public ProductInfo get(Long product_code) throws Exception {
    return productInfoMapper.select(product_code);
  }

  @Override
  public List<ProductInfo> get() throws Exception {
    return productInfoMapper.selectall();
  }

  public List<ProductInfo> selectcategoryname(String categoryname) throws Exception {
    return productInfoMapper.selectcategoryname(categoryname);
  }
}

