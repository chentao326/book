package com.DAO;

import com.pojo.User;

/**
 * @author : chentao
 * @time : 23:45 2022/6/14
 */
public interface UserDao {


    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户，反之亦然
     */
    public User queryUserByName(String username);

    /**
     * 根据用户名和密码查询用户名
     * @param username
     * @param password
     * @return 如果返回null，说明没有这个用户，反之亦然
     */
    public User queryUserBynameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 返回-1 表示失败，其他是sql影响的行数
     */
    public int saveUser(User user);

}
