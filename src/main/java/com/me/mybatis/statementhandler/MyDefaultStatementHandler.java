package com.me.mybatis.statementhandler;

import com.me.mybatis.config.MyMapperRegistry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author OuyangJie
 * @Date 2018/11/13 11:12
 * @Description:
 */
public class MyDefaultStatementHandler implements MyStatementHandler {
    private MyParameterHandler myParameterHandler;

    private MyResultSetHandler myResultSetHandler;

    public MyDefaultStatementHandler() {
        this.myParameterHandler = new MyDefaultParameterHandler();
        this.myResultSetHandler = new MyDefaultResultSetHandler();
   }

    @Override
    public <T> T query(MyMapperRegistry.MapperData mapperData, Object param) throws Exception {
        String sql = mapperData.getSql();
        sql = sql.replace("?", String.valueOf(param));
        PreparedStatement preparedStatement = myParameterHandler.getPreparedStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        return myResultSetHandler.handleResultSet(rs, mapperData.getType());
    }
}