package com.fzy.controller;

import com.fzy.entity.User;
import com.fzy.serivce.AggregationService;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

/**
 * @author Fucai
 * @date 2018/8/11
 */
@RestController
public class AggregationController {

  private static final Logger logger=LoggerFactory.getLogger(AggregationController.class);

  @Autowired
  private AggregationService aggregationService;

  @GetMapping(value = "/aggregate/{id}")
  public DeferredResult<HashMap<String,User>> aggregate(@PathVariable Long id){
    Observable<HashMap<String,User>> result=aggregateObservable(id);
    return toDeferredResult(result);

  }


  public Observable<HashMap<String,User>> aggregateObservable(Long id){
    return Observable.zip(aggregationService.getUserById(id),aggregationService.getMoviceUserById(id),(user,movieUser)->{
      HashMap<String,User> map=new HashMap<>();
      map.put("user",user);
      map.put("movieUser",movieUser);
      return map;
    });
  }

  public DeferredResult<HashMap<String,User>> toDeferredResult(Observable<HashMap<String,User>> details){
    DeferredResult<HashMap<String,User>> result=new DeferredResult<>();
    details.subscribe(new Observer<HashMap<String, User>>() {
      @Override
      public void onCompleted() {
        logger.info("完成...");
      }

      @Override
      public void onError(Throwable throwable) {
        logger.info("发生错误...",throwable);
      }

      @Override
      public void onNext(HashMap<String, User> stringUserHashMap) {
        result.setResult(stringUserHashMap);
      }
    });
    return result;
  }

}
