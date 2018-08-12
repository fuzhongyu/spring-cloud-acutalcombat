package com.fzy.feigninterface;

import com.fzy.config.FeignLogConfiguration;
import com.fzy.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Fucai
 * @date 2018/8/8
 */
//通过configuration配置，使用自定义的feign
@FeignClient(name = "microservice-provider-user",configuration = FeignLogConfiguration.class)
public interface UserFeignClient {

  @RequestMapping(value = "/{id}",method = RequestMethod.GET)
  User findById(@PathVariable("id") Long id);

}
