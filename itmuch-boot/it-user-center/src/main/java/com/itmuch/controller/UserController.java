package com.itmuch.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.itmuch.domain.dto.UserDTO;
import com.itmuch.domain.entity.User;
import com.itmuch.mapper.UserMapper;
import com.itmuch.service.UserService;
import com.itmuch.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 分享(user)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {
/**
* 服务对象
*/
    @Autowired
    private UserService userService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("/{id}")
    public UserDTO selectOne(@PathVariable Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    @GetMapping("/hot")
    @SentinelResource("hot")
    public String selectHotOne(
            @RequestParam(required = false) String a,
            @RequestParam(required = false) String b
    ) {
        return a + "-" + b;
    }
}
