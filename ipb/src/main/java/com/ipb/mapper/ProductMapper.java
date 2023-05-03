package com.ipb.mapper;


import com.ipb.domain.Product;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper extends MyMapper<Long, Product> {
}