package com.fzy.serivce;

import com.fzy.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

/**
 * @author Fucai
 * @date 2018/8/11
 */
@Service
public class AggregationService {

  @Autowired
  private RestTemplate restTemplate;

  @HystrixCommand(fallbackMethod = "fallback")
  public Observable<User> getUserById(Long id){
    return Observable.create(observer->{
      User user=restTemplate.getForObject("http://microservice-provider-user/"+id,User.class);
      observer.onNext(user);
      observer.onCompleted();
    });
  }

  @HystrixCommand(fallbackMethod = "fallback")
  public Observable<User> getMoviceUserById(Long id){
    return Observable.create(observer->{
      User movieUser=restTemplate.getForObject("http://microservice-consumer-movie/user/"+id,User.class);
      observer.onNext(movieUser);
      observer.onCompleted();
    });
  }

  public User fallback(Long id){
    User user=new User();
    user.setId(-1L);
    user.setName("zuul 熔断");
    return user;
  }

}
