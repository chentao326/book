package com.web;

import com.pojo.User;
import com.service.impl.UserServiceImpl;
import com.service.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author : chentao
 * @time : 21:00 2022/6/17
 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

// 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
// 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


//        获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        检查验证码是否正确===写死，要求验证码为  abcde
        if (token != null && token.equalsIgnoreCase(code)) {
//            检查用户名是否可用
            if (userService.exitsUsername(username)) {
//            不可用
                System.out.println("用户名【" + username + "】已存在");
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
//            跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
//            可用
//            调用service保存到数据库
                userService.registerUser(new User(null, username, password, email));
//            跳转注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
//            不正确
//            把回显信息保存至request域中
            req.setAttribute("msg", "用户名或者密码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//            跳回注册页面
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
