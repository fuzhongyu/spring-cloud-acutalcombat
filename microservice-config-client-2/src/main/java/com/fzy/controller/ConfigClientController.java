package com.fzy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fucai
 * @date 2018/8/11
 */
@RestController
public class ConfigClientController {

  @Value("${profile}")
  private String profile;

  @GetMapping(value = "/profile")
  public String getProfile(){
    return profile;
  }

}
