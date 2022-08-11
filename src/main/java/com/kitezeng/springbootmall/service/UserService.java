package com.kitezeng.springbootmall.service;

import com.kitezeng.springbootmall.dto.UserLoginRequest;
import com.kitezeng.springbootmall.dto.UserRegisterRequest;
import com.kitezeng.springbootmall.model.User;

public interface UserService {
    public abstract Integer register(UserRegisterRequest userRegisterRequest);
    public abstract User getUserById(Integer userId);
    public abstract User login(UserLoginRequest userLoginRequest);
    public abstract User getUserByEmail(String userEmail);
}
