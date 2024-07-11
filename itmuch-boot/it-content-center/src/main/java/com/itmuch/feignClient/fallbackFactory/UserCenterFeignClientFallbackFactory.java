package com.itmuch.feignClient.fallbackFactory;

import com.itmuch.domain.dto.UserDTO;
import com.itmuch.feignClient.UserCenterFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class UserCenterFeignClientFallbackFactory implements FallbackFactory<UserCenterFeignClient> {

    @Override
    public UserCenterFeignClient create(Throwable throwable) {
        return new UserCenterFeignClient() {
            @Override
            public UserDTO selectOne(Integer id) {
                log.warn("远程调用被限流/降级", throwable);
                UserDTO user = new UserDTO();
                user.setWxNickname("一个默认名称");
                return user;
            }
        };
    }
}
