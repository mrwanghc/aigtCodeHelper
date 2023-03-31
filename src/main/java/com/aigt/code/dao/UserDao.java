package com.aigt.code.dao;

import com.aigt.code.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserDao {

    List<User> getUserList(User user);
}
