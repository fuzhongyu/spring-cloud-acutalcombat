package com.fzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 * @author Fucai
 * @date 2018/8/8
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderUserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProviderUserApplication.class,args);
    }
}
