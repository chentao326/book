package com.test;

import com.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author : chentao
 * @time : 22:16 2022/6/14
 */
public class JDBCUtilsTest {
    @Test
    public void testJDBCUtils() {
        for (int i = 0; i < 100;i++) {
//            数据库连接池获取之后需要释放
            Connection conn = JDBCUtils.getConnection();
            System.out.println(conn);
//            JDBCUtils.close(conn);
        }

    }
}
