package com.kitezeng.springbootmall.dao;

import com.kitezeng.springbootmall.dto.UserRegisterRequest;
import com.kitezeng.springbootmall.model.User;

public interface UserDao {
    public abstract Integer createUser(UserRegisterRequest userRegisterRequest);
    public abstract User getUserById(Integer userId);
}
