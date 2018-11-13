package com.me.mybatis.config;

import com.me.mybatis.proxy.MyMapperProxy;
import com.me.mybatis.sqlsession.MySqlSession;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:11
 * @Description:
 */
public class MyConfiguration {
    public <T> T getMapper(Class<T> type, MySqlSession mySqlSession) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MyMapperProxy(mySqlSession));
    }

    public static class TestXml{
        public static String namespace = "com.me.mybatis.dao.TestDAO";
        public static Map<String, String> methodSqlMapping = new HashMap<>();

        static {
            methodSqlMapping.put("selectById", "select * from test where id = %d");
        }
    }
}
