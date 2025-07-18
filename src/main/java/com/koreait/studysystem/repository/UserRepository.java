package com.koreait.studysystem.repository;

import com.koreait.studysystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRepository {
    void insertUser(User user);
    User findByUsername(@Param("username") String username);
    User findById(@Param("id") Long id);
    List<User> findAll();
} 