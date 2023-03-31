package com.aigt.code.service.impl;

import com.aigt.code.dao.UserDao;
import com.aigt.code.entity.User;
import com.aigt.code.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getUserList(User user) {
        return userDao.getUserList(user);
    }
}
