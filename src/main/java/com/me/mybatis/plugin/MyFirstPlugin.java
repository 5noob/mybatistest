package com.me.mybatis.plugin;

import com.me.mybatis.config.MyMapperRegistry;
import com.me.mybatis.executor.MyExecutor;
import com.me.mybatis.statementhandler.MyParameterHandler;
import com.me.mybatis.statementhandler.MyResultSetHandler;
import com.me.mybatis.statementhandler.MyStatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author OuyangJie
 * @Date 2018/11/14 17:30
 * @Description:
 */
@Intercepts({@Signature(
        type= MyExecutor.class, method = "doQuery", args={MyMapperRegistry.MapperData.class, Object.class}
),
        @Signature(
                type = MyParameterHandler.class, method = "getPreparedStatement", args = {String.class}
        ),
        @Signature(
                type = MyResultSetHandler.class, method = "handleResultSet", args = {ResultSet.class, Class.class}
        ),
        @Signature(
                type = MyStatementHandler.class, method = "query", args = {MyMapperRegistry.MapperData.class, Object.class}
        )
})
public class MyFirstPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("=== MyFirstPlugin开始搞事情，被拦截的方法名：" + invocation.getMethod().getName());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return MyPlugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
