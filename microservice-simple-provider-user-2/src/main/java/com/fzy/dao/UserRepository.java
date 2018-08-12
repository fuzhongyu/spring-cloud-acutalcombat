package com.fzy.dao;

import com.fzy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Fucai
 * @date 2018/8/8
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
