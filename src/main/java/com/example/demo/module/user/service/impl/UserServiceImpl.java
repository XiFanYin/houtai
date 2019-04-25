package com.example.demo.module.user.service.impl;

import com.example.demo.module.user.dao.UserMapper;
import com.example.demo.module.user.entity.User;
import com.example.demo.module.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userdao;

    @Override
    public List<User> selectAllUserList(Integer page) {
        if (page==null){
            page=1;
        }
      int  pageSize = 4;
        //开始分页
        PageHelper.startPage(page, pageSize);
        List<User> empsq=userdao.selectAllUserList();
        //所有关于分页的数据全部在这个对象中储存
        PageInfo<User> pageinfo=new PageInfo<User>(empsq,7);
        List<User> list = pageinfo.getList();

        return list;
    }

    @Override
    public Integer insert(User user) {
        return userdao.insert(user);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return userdao.selectByPrimaryKey(userId);
    }
}
