package com.test;

import com.pojo.User;
import com.service.impl.UserServiceImpl;
import com.service.service.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : chentao
 * @time : 20:09 2022/6/17
 */
public class UserServiceTest {
    UserService userService = new UserServiceImpl() {

    };

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"nnn","6666","asda@qwe.com"));
        userService.registerUser(new User(null,"ewrw","6666","ewa@qwe.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"nnn","6666","null")));
        System.out.println(userService.login(new User(null,"nnn","66","null")));
    }

    @Test
    public void exitsUsername() {
        if (userService.exitsUsername("nnn")) {
    System.out.println("用户名存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}