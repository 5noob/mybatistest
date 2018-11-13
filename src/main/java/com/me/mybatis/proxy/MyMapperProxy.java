package com.me.mybatis.proxy;

import com.me.mybatis.config.MyMapperRegistry;
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
        MyMapperRegistry.MapperData mapperData = mySqlSession.getMyConfiguration()
                .getMyMapperRegistry().get(method.getDeclaringClass().getName() + "." + method.getName());
        if(null != mapperData){
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", mapperData.getSql(), args[0]));
            return mySqlSession.selectOne(mapperData, args[0]);
        }

        return method.invoke(this, args);
    }
}
