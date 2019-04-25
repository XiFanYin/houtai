package com.example.demo.module.user.service;

import com.example.demo.module.user.entity.User;

import java.util.List;

public interface UserService {

    List<User> selectAllUserList(Integer page);

    Integer insert(User user);

    User selectByPrimaryKey(Long userId);

}
