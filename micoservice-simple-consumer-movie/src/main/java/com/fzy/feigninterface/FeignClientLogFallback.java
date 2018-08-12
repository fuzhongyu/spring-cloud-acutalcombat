package com.fzy.feigninterface;

import com.fzy.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Fucai
 * @date 2018/8/9
 */
@Component
public class FeignClientLogFallback implements FallbackFactory<UserFeignClientFallback> {

  private static final Logger logger=LoggerFactory.getLogger(FeignClientLogFallback.class);


  @Override
  public UserFeignClientFallback create(Throwable throwable) {
    return new UserFeignClientFallback() {
      @Override
      public User findById(Long id) {

        FeignClientLogFallback.logger.info("fallback;reason was:",throwable);
        User user=new User();
        user.setId(-1L);
        user.setName("feign 回退-log");
        return user;
      }
    };
  }
}
