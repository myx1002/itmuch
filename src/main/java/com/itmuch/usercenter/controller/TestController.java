package com.itmuch.usercenter.controller;

import com.itmuch.usercenter.dao.user.UserMapper;
import com.itmuch.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("test")
    public User getUserInfo() {
        User user = new User();
        user.setAvatarUrl("xxxx");
        user.setWxNickname("myx");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);
        return user;
    }
}
