package com.service.service;

import com.pojo.User;

/**
 * @author : chentao
 * @time : 19:59 2022/6/17
 */
public interface UserService {
//    一个业务一个方法

    /**
     * 注册用户
     * @param user
     */
    public  void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null表示失败，返回值表示成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回ture表示用户存在，返回false表示用户不存在
     */
    public boolean exitsUsername(String username);
    }


