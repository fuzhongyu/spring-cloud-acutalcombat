package com.fzy.service;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Fucai
 * @date 2018/8/8
 */
@Getter
@Setter
public class SecurityUser implements UserDetails {

  private static final long serialVersionUID=1L;

  private Long id;

  private String username;

  private String password;

  private String role;

  public SecurityUser(String username,String password,String role){
    this.username=username;
    this.password=password;
    this.role=role;
  }

  public SecurityUser(){}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities=new ArrayList<>();
    SimpleGrantedAuthority authority=new SimpleGrantedAuthority(this.role);
    authorities.add(authority);
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

}
