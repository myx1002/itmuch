package com.itmuch.controller;

import com.itmuch.domain.dto.ShareDTO;
import com.itmuch.feignClient.TestBaiduFeignClient;
import com.itmuch.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 分享表(share)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/share")
public class TestController {
    /**
    * 服务对象
    */
    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping("/baidu")
    public String selectOne() {
        return testBaiduFeignClient.index();

    }

}
