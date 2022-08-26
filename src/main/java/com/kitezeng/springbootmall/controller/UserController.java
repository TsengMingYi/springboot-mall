package com.kitezeng.springbootmall.controller;

import com.kitezeng.springbootmall.dto.UserLoginRequest;
import com.kitezeng.springbootmall.dto.UserRegisterRequest;
import com.kitezeng.springbootmall.model.User;
import com.kitezeng.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest,HttpServletRequest request){
        //在session中取出之前存的生成好的验证码
        String verifiCode = (String) request.getSession().getAttribute("verifiCode");
        //判断用户填写验证码是否与发送的相同（相同返回登录页面提示注册成功）
        if (verifiCode.equals(userRegisterRequest.getVerificationCode())) {
            Integer userId = userService.register(userRegisterRequest);
            User user = userService.getUserById(userId);
            request.setAttribute("message","註冊成功");
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        else {
            //驗證碼驗證失敗提示驗證碼錯誤
            request.setAttribute("message","驗證碼錯誤");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
//        Integer userId = userService.register(userRegisterRequest);
//        User user = userService.getUserById(userId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
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
