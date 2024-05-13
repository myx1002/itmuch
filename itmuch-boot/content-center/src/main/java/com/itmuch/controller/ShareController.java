package com.itmuch.controller;
import com.itmuch.domain.dto.ShareDTO;
import com.itmuch.domain.entity.Share;
import com.itmuch.service.ShareService;
import com.itmuch.service.impl.ShareServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
* 分享表(share)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/share")
public class ShareController {
/**
* 服务对象
*/
    @Autowired
    private ShareService shareService;

    /**
    * 通过主键查询单条数据
    *
    * @param id 主键
    * @return 单条数据
    */
    @GetMapping("/{id}")
    public ShareDTO selectOne(@PathVariable Integer id) {
    return shareService.selectByPrimaryKey(id);
    }

}
