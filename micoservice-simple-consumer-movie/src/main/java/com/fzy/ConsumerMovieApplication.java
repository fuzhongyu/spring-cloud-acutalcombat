package com.fzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 * @author Fucai
 * @date 2018/8/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//feign配置文件不能放在spring boot的扫描包下面，所以这边指定扫描包
@ComponentScan(basePackages = {"com.fzy.controller","com.fzy.entity","com.fzy.feigninterface"})
@EnableHystrix
public class ConsumerMovieApplication
{

    @Bean
    //ribbon负载均衡注解
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main( String[] args )
    {
        SpringApplication.run(ConsumerMovieApplication.class,args);
    }
}
