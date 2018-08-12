package com.fzy;

import com.fzy.filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
public class GateWayZuulApplication {

  /**
   * 使用PreRequestLogFilter过滤器
   * @return
   */
  @Bean
  public PreRequestLogFilter preRequestLogFilter(){
    return new PreRequestLogFilter();
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(GateWayZuulApplication.class, args);
  }
}
