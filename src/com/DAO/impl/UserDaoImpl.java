package com.DAO.impl;

import com.pojo.User;

/**
 * @author : chentao
 * @time : 0:11 2022/6/15
 */
public class UserDaoImpl extends BaseDao implements com.DAO.UserDao {
    @Override
    public User queryUserByName(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=?";
        return (User) queryForOne(User.class,sql,username);

//        return null;
    }

    @Override
    public User queryUserBynameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=? and password = ?";
        return (User) queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
