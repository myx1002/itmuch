package com.itmuch.feignClient;

import com.itmuch.domain.dto.UserDTO;
import com.itmuch.feignClient.fallback.UserCenterFeignClientFallback;
import com.itmuch.feignClient.fallbackFactory.UserCenterFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "user-center",
        contextId = "UserInfoFeignClient",
//        fallback = UserCenterFeignClientFallback.class, // 拿不到异常
        fallbackFactory = UserCenterFeignClientFallbackFactory.class // 可以拿到异常信息
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
