package com.fzy.controller;

import com.fzy.dao.UserRepository;
import com.fzy.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fucai
 * @date 2018/8/8
 */
@RestController
public class UserController {

  private final static Logger logger=LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{id}")
  public User findById(@PathVariable("id") Long id){
    User findOne=userRepository.findOne(id);
    return findOne;
  }

}
