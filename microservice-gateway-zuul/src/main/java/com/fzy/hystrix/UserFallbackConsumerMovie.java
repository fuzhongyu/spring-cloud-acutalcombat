package com.fzy.hystrix;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * zuul内部整合了hystrix熔断机制，下面是对熔断返回做处理
 * @author Fucai
 * @date 2018/8/11
 */
@Component
public class UserFallbackConsumerMovie implements ZuulFallbackProvider {

  @Override
  public String getRoute() {
    /**
     * 表名是对哪个微服务提供回退
     */
    return "microservice-consumer-movie";
  }

  @Override
  public ClientHttpResponse fallbackResponse() {
    return new ClientHttpResponse() {
      @Override
      public HttpStatus getStatusCode() throws IOException {
        //fallback时的返回状态
        return HttpStatus.OK;
      }

      @Override
      public int getRawStatusCode() throws IOException {
        //请求状态码,本例返回的实际就是200
        return this.getStatusCode().value();
      }

      @Override
      public String getStatusText() throws IOException {
        //状态文本，本例返回的其实就是ok
        return this.getStatusCode().getReasonPhrase();
      }

      @Override
      public void close() {

      }

      @Override
      public InputStream getBody() throws IOException {
        return new ByteArrayInputStream("微服务不可用，请稍后再试".getBytes());
      }

      @Override
      public HttpHeaders getHeaders() {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
      }
    };
  }
}
