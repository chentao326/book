package com.web;

import com.google.gson.Gson;
import com.pojo.User;
import com.service.impl.UserServiceImpl;
import com.service.service.UserService;
import com.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author : chentao
 * @time : 21:45 2022/6/18
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private HttpServletRequest req;
    private HttpServletResponse resp;

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数username
        String username = req.getParameter("username");
        // 调用userService.existsUsername();
        boolean existsUsername = userService.exitsUsername(username);
        // 把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    /**
     * 注销
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
//        2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
    }


    /**
     * 登录功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        调用xxx.service.xxx()处理业务//        userService.login()登录
        User loginUser = userService.login(new User(null, username, password, null));
//        如果!=null,说明登录成功
        if (loginUser != null) {
//          保存用户登录的信息到Session域中
            req.getSession().setAttribute("user", loginUser);
            //成功   跳转成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);

        } else {
            req.setAttribute("msg", "用户名或者密码错误");
            req.setAttribute("username", username);
            //失败   跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 注册功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //        获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

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
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//            跳回注册页面
        }
    }




//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
////        利用反射，减少代码量
//        try {
//            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
//            method.invoke(this,req,resp);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        if (action.equals("login")) {
//            login(req, resp);
//        } else if (action.equals("regist")) {
//            regist(req, resp);
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }
}

