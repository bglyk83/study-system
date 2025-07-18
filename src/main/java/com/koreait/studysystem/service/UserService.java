package com.koreait.studysystem.service;

import com.koreait.studysystem.entity.User;

public interface UserService {
    void register(User user);
    User findByUsername(String username);
    User findById(Long id);
} 