package com.ipb.mapper;


import com.ipb.domain.StoreProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmitterMapper {
  void saveStoreProduct(StoreProduct storeProduct);

  List<StoreProduct> findProductsExpiringSoon(int days);
}
