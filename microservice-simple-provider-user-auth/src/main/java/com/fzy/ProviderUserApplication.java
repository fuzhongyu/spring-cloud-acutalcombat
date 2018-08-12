package com.fzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 * @author Fucai
 * @date 2018/8/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.fzy.controller","com.fzy.dao","com.fzy.entity","com.fzy.service"})
public class ProviderUserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProviderUserApplication.class,args);
    }
}
