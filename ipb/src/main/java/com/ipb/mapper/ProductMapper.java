package com.ipb.mapper;


import com.ipb.domain.Product;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper extends MyMapper<Long, Product> {
  public List<Product> selectcategoryname(String categoryname) throws Exception;
}