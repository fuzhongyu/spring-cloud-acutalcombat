package com.fzy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fucai
 * @date 2018/8/11
 */
@RestController
//手动刷新配置,需要访问http://localhost:8081/refresh 去手动刷新
@RefreshScope
public class ConfigClientRefreshController {

  @Value("${profile}")
  private String profile;

  @GetMapping(value = "/profile_refresh")
  public String getProfile(){
    return profile;
  }

}
