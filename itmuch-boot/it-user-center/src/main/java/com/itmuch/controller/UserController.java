package com.itmuch.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.itmuch.domain.dto.UserDTO;
import com.itmuch.domain.entity.User;
import com.itmuch.mapper.UserMapper;
import com.itmuch.sentinel.BlockHandler;
import com.itmuch.service.UserService;
import com.itmuch.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
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

    @GetMapping("/sentinel/resource")
    @SentinelResource(
            value = "getUserResource",
            blockHandler = "blockHandlerForGetUserResource", // 方式一：当请求被Sentinel的限流或熔断规则阻塞时，此方法将被调用。
//            blockHandlerClass = BlockHandler.class, // 方式二：当请求被Sentinel的限流或熔断规则阻塞时，此方法将被调用。
            fallback = "fallbackForGetUserResource", // 这是一个fallback方法，当fetchData方法抛出异常时，此方法将被调用，除了被exceptionsToIgnore排除的异常。
            exceptionsToIgnore = {IllegalArgumentException.class} // 哪些异常会被忽略。
    )
    public String getUserResource(@RequestParam(required = false) String a) {
        if (Strings.isBlank(a)) {
            throw new IllegalArgumentException("a不能为空");
        }
        if (a.equals("1")) {
            throw new RuntimeException("a不能等于1");
        }
        return a + "-";
    }

    public String blockHandlerForGetUserResource(String a, BlockException e) {
        return "blockHandlerForGetUserResource";
    }

    public String fallbackForGetUserResource(String a, Throwable e) {
        return "fallbackForGetUserResource";
    }
}
