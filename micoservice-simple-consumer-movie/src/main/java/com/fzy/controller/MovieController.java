package com.fzy.controller;

import com.fzy.entity.User;
import com.fzy.feigninterface.UserFeignClient;
import com.fzy.feigninterface.UserFeignClientFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Fucai
 * @date 2018/8/8
 */

@RestController
public class MovieController {

  private static final Logger logger=LoggerFactory.getLogger(MovieController.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @Autowired
  private UserFeignClient userFeignClient;

  @Autowired
  private UserFeignClientFallback userFeignClientFallback;

  /**
   * 服务调用测试
   * @param id
   * @return
   */
  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id){
//    return restTemplate.getForObject("http://localhost:8000/"+id,User.class);
    /**
     * 当使用ribbon的时候(@bean restTemplate中加了ribbon)，可直接将地址改为服务的名称，即下文的microservice-provider-user
     */
    return restTemplate.getForObject("http://microservice-provider-user/"+id,User.class);
  }

  /**
   * 元数据获取测试
   * @return
   */
  @GetMapping("user_instance")
  public List<ServiceInstance> showInfo(){
    List<ServiceInstance> serviceInstances=discoveryClient.getInstances("microservice-provider-user");
    System.out.println("----->"+serviceInstances.get(0).getMetadata());
    return serviceInstances;
  }

  /**
   * ribbon测试
   *
   */
  @GetMapping("/log-instance")
  public void logUserInstance(){
    /**
     * 注：(1)不能将restTemplate.getForObject()与loadBlancerClient.choose()写在一起,两者会有冲突，
     * 因为此时代码中的restTemplate实际上是一个ribbon客户端，本身已经包含了choose行为
     *    (2) 主机名不能包含"_"之类字符，否则ribbon会在调用的时候报异常
     */
    ServiceInstance serviceInstance=this.loadBalancerClient.choose("microservice-provider-user");
    MovieController.logger.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());
  }

  /**
   * feign测试
   */
  @GetMapping(value = "/user_2/{id}")
  public User findById_2(@PathVariable Long id){
    return userFeignClient.findById(id);
  }

  /**
   * hystrix熔断测试
   */
  @HystrixCommand(fallbackMethod = "findByIdFallback",commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
      @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000")
  },threadPoolProperties = {
      @HystrixProperty(name = "coreSize",value = "1"),
      @HystrixProperty(name = "maxQueueSize",value = "10")
  })
  @GetMapping("/user_3/{id}")
  public User findById_3(@PathVariable Long id){
    return userFeignClient.findById(id);
  }

  /**
   * 错误处理
   * @param id
   * @return
   */
  public User findByIdFallback(Long id){
    User user=new User();
    user.setId(-1L);
    user.setName("默认用户");
    return user;
  }

  /**
   * feign回退测试
   */
  @GetMapping("/user_4/{id}")
  public User findById_4(@PathVariable Long id){
    return userFeignClientFallback.findById(id);
  }

}
