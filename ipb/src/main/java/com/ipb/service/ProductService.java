package com.ipb.service;



import com.ipb.domain.Product;
import com.ipb.domain.StoreProduct;
import com.ipb.frame.MyService;
import com.ipb.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
  public List<Product> orderlistproduct(Long store_id) throws Exception {
    return productMapper.orderlistproduct(store_id);
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

//  본사 상품을 검색하여 조회한다
  public List<Product> search(String search) throws Exception {
    return productMapper.search(search);
  }

  //발주를 했을 때 PRODUCT 테이블의 상품재고 수량을 변경
  public void updateQnt(Product product) throws Exception {
    productMapper.updateQnt(product);
  }

//  자동 발주 할때 해당 상품코드와 동일한 상품목록을 가져온다. (본사에서 _ 유통기간이 얼마 안남은게 처음에 온다.)
  public List<Product> getProductListByProductCode(Long productCode) throws Exception {
    return productMapper.getProductListByProductCode(productCode);
  }

//  ex) 상품코드가 같은 경우 수량 전체를 가져옴 ex)사과가 5/23일꺼 5/24일꺼 100개 씩 있으면 총200개로 들고옴
//  자동 발주가 될 때 본사에 등록되어있는 사과가 있는데 100개가 5/2 ,100개가 5/3 있으면 총 갯수 가져오고 본사에서 선입선출하는 느낌으로
//  자동 발주가 이루어질 때 발주되는 qnt 만큼의 상품을 가져오기 위해 본사에서 보유한 상품의 상품코드를 통해 상품 전체 수량을 계산함
  public int getProductQntByProductCode(Long productCode) throws Exception {
    List<Product> productList = getProductListByProductCode(productCode);
    int productQnt = 0;
    for (Product product : productList) {
      productQnt += product.getQnt();
    }
    return productQnt;
  }

  public boolean findProductCodeByEventIdAndCompareProdCode(Long previousEventId, Long productCode) throws Exception {
    return productMapper.findProductCodeByEventIdAndCompareProdCode(previousEventId, productCode) != null;
  }
  public List<Product> findProductByCode (Long product_code) throws Exception {
    return productMapper.getProductListByProductInfoQnt(product_code);
  }
  public List<Product> getAvailableProductListByProductInfo (Long product_info_id) throws Exception {
    return productMapper.getAvailableProductListByProductInfo(product_info_id);
  }
}

