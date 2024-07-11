package com.itmuch.feignClient.fallback;

import com.itmuch.domain.dto.UserDTO;
import com.itmuch.feignClient.UserCenterFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserCenterFeignClientFallback implements UserCenterFeignClient {
    /**
     * 调用失败时，会回调使用该方法
     * @param id
     * @return
     */
    @Override
    public UserDTO selectOne(Integer id) {
        UserDTO user = new UserDTO();
        user.setWxNickname("一个默认名称");
        return user;
    }
}
