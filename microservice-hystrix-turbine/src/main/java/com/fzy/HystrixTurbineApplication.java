package com.fzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
//import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
//turbine多应用监控
//@EnableTurbine
//rabbion接收日志监控
@EnableTurbineStream
public class HystrixTurbineApplication {

  public static void main(String[] args) {
    SpringApplication.run(HystrixTurbineApplication.class, args);
  }
}
