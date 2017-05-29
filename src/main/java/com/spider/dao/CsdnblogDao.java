package com.spider.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.spider.entity.Csdnblog;

@Mapper
public interface CsdnblogDao {

    int insert(@Param("pojo") Csdnblog pojo);

    int insertSelective(@Param("pojo") Csdnblog pojo);

    int insertList(@Param("pojos") List<Csdnblog> pojo);

    int update(@Param("pojo") Csdnblog pojo);
}
