package com.fzy.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Fucai
 * @date 2018/8/8
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    if ("user".equals(s)){
      return new SecurityUser("user","password1","user-role");
    }else if ("admin".equals(s)){
      return new SecurityUser("admin","password2","admin-role");
    }else {
      return null;
    }

  }
}
