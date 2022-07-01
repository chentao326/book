package com.web;

import com.pojo.User;
import com.service.impl.UserServiceImpl;
import com.service.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : chentao
 * @time : 9:55 2022/6/18
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        调用xxx.service.xxx()处理业务
//        userService.login()登录
        User  loginUser =  userService.login(new User(null,username,password,null));
//        如果!=null,说明登录成功
        if (loginUser != null) {
            //        成功   跳转成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }else {
            req.setAttribute("msg","用户名或者密码错误");
            req.setAttribute("username",username);
            //        失败   跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
