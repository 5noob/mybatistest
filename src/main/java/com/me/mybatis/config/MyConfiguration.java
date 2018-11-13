package com.me.mybatis.config;

import com.me.mybatis.proxy.MyMapperProxy;
import com.me.mybatis.sqlsession.MySqlSession;
import lombok.Data;

import java.lang.reflect.Proxy;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:11
 * @Description:
 */
@Data
public class MyConfiguration {
    private MyMapperRegistry myMapperRegistry = new MyMapperRegistry();

    public <T> T getMapper(Class<T> type, MySqlSession mySqlSession) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MyMapperProxy(mySqlSession));
    }
}
