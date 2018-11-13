package com.me.mybatis.proxy;

import com.me.mybatis.config.MyConfiguration;
import com.me.mybatis.sqlsession.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:15
 * @Description:
 */
public class MyMapperProxy implements InvocationHandler {

    private MySqlSession mySqlSession;

    public MyMapperProxy(MySqlSession mySqlSession) {
        this.mySqlSession = mySqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(MyConfiguration.TestXml.namespace)){
            String sql = MyConfiguration.TestXml.methodSqlMapping.get(method.getName());
            return mySqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this, args);
    }
}
