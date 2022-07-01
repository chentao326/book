package com.DAO.impl;

import com.utils.JDBCUtils_withoutJar;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : chentao
 * @time : 22:33 2022/6/8
 */
public abstract class BaseDAO_withoutJar<T> {

    private Class<T> clazz = null;

    {
//        获取当前BaseDAO的子类继承的父类的泛型
        Type genericSuperclass= this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

        Type[] typeArguments = parameterizedType.getActualTypeArguments();//获取父类的泛型参数
        clazz = (Class<T>) typeArguments[0];
    }

    public int Update(Connection conn, String sql, Object... args) {
        /**
         * 考虑数据库事务
         */
        //增删改
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                //小心参数声明错误！！
                ps.setObject(i + 1, args[i]);
            }
            //3.执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            try {
                JDBCUtils_withoutJar.closeResource(null, ps);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    //针对不同表的通用的查询操作，返回表的一条记录
    public T gerInstance(Connection conn, String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            conn = JDBCUtils_withoutJar.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
//            获取结果集的元数据，ResultSetMetaData
            rs = ps.executeQuery();
//            通过ResultSetMetaData获取结果集中的列数
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
//                处理结果集一行数据中的每一列
                T t = clazz.newInstance();
                for (int j = 0; j < columnCount; j++) {
//                    获取对象
                    Object columValue = rs.getObject(j + 1);
//                    获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(j + 1);
//                    给t对象指定的columnName属性，赋值为columnValue,通过反射
                    Field field = clazz.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_withoutJar.closeResource(null, ps, rs);
        }
        return null;
    }


    // 针对不同表的通用的查询操作，返回表的多条记录
    public List<T> getForList(Connection conn, String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            conn = JDBCUtils_withoutJar.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
//            获取结果集的元数据，ResultSetMetaData
            rs = ps.executeQuery();
//            通过ResultSetMetaData获取结果集中的列数
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
//            创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
//                处理结果集一行数据中的每一列
                T t = clazz.newInstance();
//                给t对象指定的属性赋值
                for (int j = 0; j < columnCount; j++) {
//                    获取对象
                    Object columValue = rs.getObject(j + 1);
//                    获取每个列的列名
                    String columnLabel = rsmd.getColumnLabel(j + 1);
//                    给t对象指定的columnName属性，赋值为columnValue,通过反射
                    Field field = clazz.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_withoutJar.closeResource(null, ps, rs);
        }
        return null;
    }

    //    查询特殊值的通用方法
    public <E> E getValue(Connection conn, String sql, Object... args) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils_withoutJar.closeResource(null, ps, rs);
        }
        return null;

    }

}
