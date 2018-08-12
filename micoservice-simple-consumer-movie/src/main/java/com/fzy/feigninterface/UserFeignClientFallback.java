package com.fzy.feigninterface;

import com.fzy.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Fucai
 * @date 2018/8/8
 */
//不需要输出调用失败的配置
//@FeignClient(name = "microservice-provider-user",fallback = FeignClientFallback.class)
  //需要输出调用失败的配置
  @FeignClient(name = "microservice-provider-user",fallbackFactory = FeignClientLogFallback.class)
public interface UserFeignClientFallback {

  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  User findById(@PathVariable("id") Long id);

}
