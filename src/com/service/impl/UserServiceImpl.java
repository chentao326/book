package com.service.impl;

import com.DAO.UserDao;
import com.DAO.impl.UserDaoImpl;
import com.pojo.User;
import com.service.service.UserService;

/**
 * @author : chentao
 * @time : 20:04 2022/6/17
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserBynameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean exitsUsername(String username) {
        if (userDao.queryUserByName(username) == null){
            return false;//==null 说明没查到，没查到表示可用
        }
        return true;
    }
}
