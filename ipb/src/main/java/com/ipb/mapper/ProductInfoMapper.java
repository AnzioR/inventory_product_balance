package com.ipb.mapper;


import com.ipb.domain.Product;
import com.ipb.domain.ProductInfo;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductInfoMapper extends MyMapper<Long, ProductInfo> {
  public List<ProductInfo> selectcategoryname(String categoryname) throws Exception;

  ProductInfo getProductInfoByProductId(Long productId);
}