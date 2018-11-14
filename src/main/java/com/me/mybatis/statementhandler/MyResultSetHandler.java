package com.me.mybatis.statementhandler;

import java.sql.ResultSet;

/**
 * @author OuyangJie
 * @Date 2018/11/14 17:52
 * @Description:
 */
public interface MyResultSetHandler {
    <T> T handleResultSet(ResultSet rs, Class type) throws Exception;
}
