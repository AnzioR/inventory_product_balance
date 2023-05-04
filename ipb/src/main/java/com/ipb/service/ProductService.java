package com.ipb.service;

import com.ipb.domain.Product;
import com.ipb.frame.MyService;
import com.ipb.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService implements MyService <Long, Product> {

  @Autowired
  ProductMapper productMapper;

  @Override
  public void register(Product product) throws Exception {
    productMapper.insert(product);
  }

  @Override
  public void modify(Product product) throws Exception {
    productMapper.update(product);
  }

  @Override
  public void remove(Long id) throws Exception {
    productMapper.delete(id);
  }

  @Override
  public Product get(Long id) throws Exception {
    return productMapper.select(id);
  }

  @Override
  public List<Product> get() throws Exception {
    return productMapper.selectall();
  }
  public List<Product> selectcategoryname(String categoryname) throws Exception {
    return productMapper.selectcategoryname(categoryname);
  }
}

