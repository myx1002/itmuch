package com.itmuch.feignClient;

import com.itmuch.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "user-center",
        contextId = "UserInfoFeignClient"
)
public interface UserCenterFeignClient {

    /**
     * 通过主键查询单条数据
     * @param id
     * @return 单条数据
     */
    @GetMapping("/users/{id}")
    UserDTO selectOne(@PathVariable("id") Integer id);
}
