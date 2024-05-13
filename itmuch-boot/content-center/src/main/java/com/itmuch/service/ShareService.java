package com.itmuch.service;

import com.itmuch.domain.dto.ShareDTO;
import com.itmuch.domain.entity.Share;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ShareService extends IService<Share> {


    int updateBatch(List<Share> list);

    int updateBatchSelective(List<Share> list);

    int batchInsert(List<Share> list);

    int insertOrUpdate(Share record);

    int insertOrUpdateSelective(Share record);

    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertSelective(Share record);

    ShareDTO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);
}


