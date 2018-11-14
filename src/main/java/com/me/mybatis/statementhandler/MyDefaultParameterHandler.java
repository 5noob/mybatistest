package com.me.mybatis.statementhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author OuyangJie
 * @Date 2018/11/13 11:13
 * @Description:
 */
public class MyDefaultParameterHandler implements MyParameterHandler {

    @Override
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection connection;
        try {
            connection = getConnection();
        } catch (Exception e) {
            throw new RuntimeException("获取数据库连接失败");
        }

        return connection.prepareStatement(sql);
    }

    public Connection getConnection() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mybatis_test?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "NewPwd@123";
        Connection conn = null;
        //classLoader加载对应驱动
        Class.forName(driver);
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
}
