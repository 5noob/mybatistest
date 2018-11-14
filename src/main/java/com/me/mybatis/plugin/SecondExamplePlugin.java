package com.me.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author OuyangJie
 * @Date 2018/11/13 20:57
 * @Description:
 */
@Intercepts({@Signature(
        type= Executor.class, method = "query", args={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}
        ),
        @Signature(
                type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}
        ),
        @Signature(
                type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}
        )
})
public class SecondExamplePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("==== SecondExamplePlugin 开始搞事情：" + invocation.getMethod().getName() + "  ====");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
