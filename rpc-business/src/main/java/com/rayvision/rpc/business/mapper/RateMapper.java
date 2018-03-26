package com.rayvision.rpc.business.mapper;

import com.rayvision.rpc.business.entity.Rate;
import com.rayvision.rpc.business.entity.RateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateMapper {
    long countByExample(RateExample example);

    int deleteByExample(RateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rate record);

    int insertSelective(Rate record);

    List<Rate> selectByExample(RateExample example);

    Rate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rate record, @Param("example") RateExample example);

    int updateByExample(@Param("record") Rate record, @Param("example") RateExample example);

    int updateByPrimaryKeySelective(Rate record);

    int updateByPrimaryKey(Rate record);
}