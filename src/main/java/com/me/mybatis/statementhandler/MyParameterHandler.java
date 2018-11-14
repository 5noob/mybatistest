package com.me.mybatis.statementhandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author OuyangJie
 * @Date 2018/11/14 17:50
 * @Description:
 */
public interface MyParameterHandler {
    PreparedStatement getPreparedStatement(String sql) throws SQLException;
}
