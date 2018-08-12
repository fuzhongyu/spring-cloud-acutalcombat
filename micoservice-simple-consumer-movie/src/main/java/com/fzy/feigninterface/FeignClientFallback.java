package com.fzy.feigninterface;

import com.fzy.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Fucai
 * @date 2018/8/9
 */
@Component
public class FeignClientFallback implements UserFeignClientFallback {

  @Override
  public User findById(Long id) {
    User user=new User();
    user.setId(-1L);
    user.setName("feign 回退");
    return user;
  }
}
