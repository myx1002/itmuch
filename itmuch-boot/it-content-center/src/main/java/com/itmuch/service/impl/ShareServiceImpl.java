package com.itmuch.service.impl;

import com.itmuch.domain.dto.ShareDTO;
import com.itmuch.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmuch.domain.entity.Share;
import com.itmuch.mapper.ShareMapper;
import com.itmuch.service.ShareService;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {

    // 分享服务
    private final ShareMapper shareMapper;

    private final RestTemplate restTemplate;

    private final DiscoveryClient discoveryClient;

    @Override
    public int updateBatch(List<Share> list) {
        return shareMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Share> list) {
        return shareMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Share> list) {
        return shareMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Share record) {
        return shareMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Share record) {
        return shareMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return shareMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Share record) {
        return shareMapper.insert(record);
    }

    @Override
    public int insertSelective(Share record) {
        return shareMapper.insertSelective(record);
    }

    @Override
    public ShareDTO selectByPrimaryKey(Integer id) {
        Share shareInfo = shareMapper.selectByPrimaryKey(id);
        Integer userId = shareInfo.getUserId();

        List<ServiceInstance> instances = discoveryClient.getInstances("it-user-center");
        String userServiceHost = instances.stream()
                .map(instance -> instance.getUri().toString() + "users/{id}")
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("获取用户信息失败！"));
        // http调用用户服务，获取用户信息
        ResponseEntity<UserDTO> userDTO = this.restTemplate.getForEntity(
                userServiceHost, UserDTO.class, userId
        );

        ShareDTO res = new ShareDTO();
        BeanUtils.copyProperties(shareInfo, res);
        res.setWxNickName(Objects.requireNonNull(userDTO.getBody()).getWxNickname());
        return res;
    }

    @Override
    public int updateByPrimaryKeySelective(Share record) {
        return shareMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Share record) {
        return shareMapper.updateByPrimaryKey(record);
    }
}


