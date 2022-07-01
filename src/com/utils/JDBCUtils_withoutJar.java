package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 *
 * @author : chentao
 * @time : 19:48 2022/5/22
 */
public class JDBCUtils_withoutJar {

    static String driver = null;
    static String url = null;
    static String username = null;
    static String password = null;
    private Connection conn = null;

    public static Connection getConnection() throws Exception {
        /**
         * 获取数据库连接
         */
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties props = new Properties();
        props.load(in);

        driver = props.getProperty("driver");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");


        // 2.加载驱动
        Class.forName(driver);
        // 3.获取连接,写到下面方法中
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void closeResource(Connection conn, Statement ps) throws IOException, ClassNotFoundException {
        try {
            if (ps !=null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    关闭资源（含查询的关闭）
    public static void closeResource(Connection conn, Statement ps,ResultSet rs){
        try {
            if (ps !=null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
