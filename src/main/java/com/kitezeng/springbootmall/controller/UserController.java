package com.kitezeng.springbootmall.controller;

import com.kitezeng.springbootmall.dto.UserLoginRequest;
import com.kitezeng.springbootmall.dto.UserRegisterRequest;
import com.kitezeng.springbootmall.model.User;
import com.kitezeng.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String userName){
        User user = userService.getUserByEmail(userName);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
