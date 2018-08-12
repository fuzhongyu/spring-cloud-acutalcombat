package com.fzy.entity;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Fucai
 * @date 2018/8/8
 */
@Getter
@Setter
public class User {

  private Long id;

  private String username;

  private String name;

  private Integer age;

  private BigDecimal balance;


}
