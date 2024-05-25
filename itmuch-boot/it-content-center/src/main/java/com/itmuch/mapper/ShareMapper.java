package com.itmuch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmuch.domain.entity.Share;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShareMapper extends BaseMapper<Share> {
    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertOrUpdate(Share record);

    int insertOrUpdateSelective(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);

    int updateBatch(List<Share> list);

    int batchInsert(@Param("list") List<Share> list);

    int updateBatchSelective(List<Share> list);
}