package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author : chentao
 * @time : 22:49 2022/6/18
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post请求中文乱码问题
        // 一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("UTF-8");
        // 解决响应的中文乱码
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
//        利用反射，减少代码量
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
//        if (action.equals("login")) {
//            login(req, resp);
//        } else if (action.equals("regist")) {
//            regist(req, resp);
//        }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);// 把异常抛给Filter过滤器
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
