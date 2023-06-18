package com.ipb.mapper;


import com.ipb.domain.Orders;
import com.ipb.domain.OrdersCart;
import com.ipb.domain.Product;
import com.ipb.domain.StoreProduct;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper extends MyMapper<Long, Product> {
  public List<Product> selectcategoryname(String categoryname) throws Exception;

  List<Product> search(String txt) throws Exception;

  public List<Product> orderlistproduct(Long store_id) throws Exception;

  //발주할 때, 재고를 변경
  public void updateQnt(Product product) throws Exception;

  //  //발주를 했을 때 PRODUCT 테이블의 상품재고 수량을 변경
  //발주할 때, 수량을 변경해줌, 처음에 발주를 하면 무조건 상품에서 수량을 뺐는데 배송 준비 중 일때는 수량이 변경 되면 안되니까
  //사용하지 않는 기능 (민-란)
//  public void updateQntAll(List<OrdersCart> orderableList) throws Exception;
  List<Product> getProductListByProductInfoQnt(Long productCode) throws Exception;

  List<Product> getProductListByProductCode(Long productCode) throws Exception;

  public Boolean findProductCodeByEventIdAndCompareProdCode(Long previousEventId, Long productCode) throws Exception;

  public List<Product> getAvailableProductListByProductInfo(Long product_info_id) throws Exception;
}