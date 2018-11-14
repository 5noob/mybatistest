package com.me.mybatis.config;

import com.me.mybatis.annotation.SqlAnno;
import com.me.mybatis.executor.MyExecutor;
import com.me.mybatis.executor.MyExecutorFactory;
import com.me.mybatis.plugin.MyInterceptorChain;
import com.me.mybatis.proxy.MyMapperProxy;
import com.me.mybatis.sqlsession.MySqlSession;
import com.me.mybatis.statementhandler.*;
import com.me.mybatis.util.PackageUtil;
import lombok.Data;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:11
 * @Description:
 */
@Data
public class MyConfiguration {
    private MyMapperRegistry myMapperRegistry = new MyMapperRegistry();
    private MyInterceptorChain myInterceptorChain = new MyInterceptorChain();

    public MyConfiguration() {
    }

    public MyExecutor newExecutor(){
        MyExecutor myExecutor = MyExecutorFactory.getExecutor(MyExecutorFactory.ExecutorType.CACHING.name(), this);
        myExecutor = (MyExecutor) myInterceptorChain.pluginAll(myExecutor);
        return myExecutor;
    }

    public MyParameterHandler newParameterHandler(){
        MyParameterHandler myParameterHandler = new MyDefaultParameterHandler();
        myParameterHandler = (MyParameterHandler) myInterceptorChain.pluginAll(myParameterHandler);
        return myParameterHandler;
    }

    public MyResultSetHandler newResutlSetHandler(){
        MyResultSetHandler myResultSetHandler = new MyDefaultResultSetHandler();
        myResultSetHandler = (MyResultSetHandler) myInterceptorChain.pluginAll(myResultSetHandler);
        return myResultSetHandler;
    }

    public MyStatementHandler newStatementHandler(){
        MyStatementHandler myStatementHandler = new MyDefaultStatementHandler(this);
        myStatementHandler = (MyStatementHandler) myInterceptorChain.pluginAll(myStatementHandler);
        return myStatementHandler;
    }

    public void scanPath(String packageName){
        //获取包名下的所有类
        Set<Class<?>> classSet = PackageUtil.getClasses(packageName);

        for(Class clazz : classSet){
            if(!clazz.isInterface()){
                continue;
            }
            //扫描接口上的SqlAnno注解
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method: methods){
                Annotation[] annotations = method.getDeclaredAnnotations();
                for(Annotation annotation: annotations){
                    if(annotation instanceof SqlAnno){
                        String sql = ((SqlAnno) annotation).value();
                        if("".equals(sql)){
                            throw new RuntimeException("SqlAnno注解的值不能为空");
                        }
                        String key = method.getDeclaringClass().getName() + "." + method.getName();
                        MyMapperRegistry.MapperData mapperData = myMapperRegistry.new MapperData(sql, method.getReturnType());
                        myMapperRegistry.put(key, mapperData);
                    }
                }
            }
        }

    }

    public <T> T getMapper(Class<T> type, MySqlSession mySqlSession) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MyMapperProxy(mySqlSession));
    }
}
