package com.ipb.mapper;


import com.ipb.domain.Orders;
import com.ipb.domain.OrdersCart;
import com.ipb.domain.Product;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper extends MyMapper<Long, Product> {
  public List<Product> selectcategoryname(String categoryname) throws Exception;
  List<Product> search(String txt) throws Exception;

  //발주할 때, 재고를 변경
  public void updateQnt(Product product) throws Exception;

  //발주를 했을 때 PRODUCT 테이블의 상품재고 수량을 변경
  public void updateQntAll(List<OrdersCart> orderableList) throws Exception;

  List<Product> getProductListByProductCode(Long productCode) throws Exception;
}