package com.fzy.config;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义feign
 * @author Fucai
 * @date 2018/8/9
 */
@Configuration
public class FeignLogConfiguration {


    @Bean
  Level feignLoggerLevel(){
    return Level.FULL;
  }
}
