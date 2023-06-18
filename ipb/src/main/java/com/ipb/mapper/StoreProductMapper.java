package com.ipb.mapper;


import com.ipb.domain.*;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface StoreProductMapper extends MyMapper<Long, StoreProduct> {
  public List<StockInfo> selectcategoryname(HashMap<String, Object> map);

  public List<StockInfo> selectexpAndExpiringSoon(HashMap<String, Object> map);

  public void updateqnt(StoreProduct StoreProduct) throws Exception;

  public List<StoreProduct> select() throws Exception;

  public List<StockInfo> searchstoreproduct(HashMap<String, Object> map);

  public List<StoreProduct> selectstoreproduct(Long txt) throws Exception;


  //발주할 때, 점포의 재고수량을 변경
  public void storeUpdateQnt(StoreProduct storeProduct) throws Exception;

  //발주할 때, store_id 와 product_id 를 같이 조회함
  public StoreProduct getStoreProductFromStoreIdAndProductId(StoreProduct st) throws Exception;

  public List<StoreProduct> getStoreProdListByProdCodeAndStoreId(Long productCode, Long storeId) throws Exception;

  //폐기했을 때 상품의 qnt=0으로 변경
  public void qntZero(StoreProduct storeProduct) throws Exception;

  Integer getStoreProductQntByStoreIdAndProductCode(Long store_id, Long product_code);

  List<StoreProduct> getProductsBetweenExpiring(Long max, Long min, Long store_id);

  List<StoreProduct> selectallStore();

  List<StoreProduct> selectallexpStoreProduct(Long store_id);

  List<StoreProduct> getProductsExpiringInThreeDays(Long storeId);

  List<StoreProduct> getLowInventoryProducts(Long storeId);


  //자동발주를 신청 안한 상품들 중에서 현재 재고량이 안전재고량보다 적은 상품들을 리스트로 가져온다.
  public List<StoreProduct> notAutoLessQnt() throws Exception;

  List<StoreProduct> getExpiringStoreProductsms(Long store_id, int days);
}


