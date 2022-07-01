package com.test;

import com.DAO.UserDao;
import com.DAO.impl.UserDaoImpl;
import com.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : chentao
 * @time : 0:38 2022/6/15
 */
public class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByName() {

        if (userDao.queryUserByName("admin")==null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名存在");
        }
    }

    @Test
    public void queryUserBynameAndPassword() {
        if(userDao.queryUserBynameAndPassword("admin","123")==null){
            System.out.println("登录失败");
        }else {
            System.out.println("查询成功");
        }

    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"aaa","123456","123@123.com")));

    }
}