package com.fzy.controller;

import com.fzy.dao.UserRepository;
import com.fzy.entity.User;
import java.util.Collection;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

  /**
   * feign认证测试
   * @param id
   * @return
   */
  @GetMapping(value = "/auth/{id}")
  public User findByIdAuth(@PathVariable Long id){
    Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails){
      UserDetails userDetails= (UserDetails) principal;
      Collection<? extends GrantedAuthority> collection=userDetails.getAuthorities();
      for (GrantedAuthority c:collection){
        UserController.logger.info("当前用户是{},角色是{}",userDetails.getUsername(),c.getAuthority());
      }
    }else {

    }

    User findOne=userRepository.findOne(id);
    return findOne;
  }
}
