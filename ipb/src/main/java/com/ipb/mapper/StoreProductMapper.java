package com.ipb.mapper;


import com.ipb.domain.*;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface StoreProductMapper extends MyMapper<Long, StoreProduct> {
  public List<StockInfo> selectcategoryname(HashMap<String,Object> map) ;

  public List<StoreProduct> select() throws Exception;
  public List<StockInfo> searchstoreproduct(HashMap<String,Object> map);

  public List<StockInfo> selectstoreproduct(Long txt) throws Exception;
//  public void updateQuantity(Long id, Integer qnt) throws Exception;

}