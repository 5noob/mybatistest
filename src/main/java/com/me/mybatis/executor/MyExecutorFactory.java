package com.me.mybatis.executor;

import com.me.mybatis.config.MyConfiguration;

/**
 * @author OuyangJie
 * @Date 2018/11/13 14:33
 * @Description:
 */
public class MyExecutorFactory {
    public static final String SIMPLE = "simple";
    public static final String CACHING = "caching";

    public static MyExecutor getDefaultExecutor(MyConfiguration myConfiguration){
        return getExecutor(SIMPLE, myConfiguration);
    }

    public static MyExecutor getExecutor(String key, MyConfiguration myConfiguration) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new MySimpleExecutor(myConfiguration);
        }
        if (CACHING.equalsIgnoreCase(key)) {
            return new MyCachingExecutor(new MySimpleExecutor(myConfiguration));
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType{
        SIMPLE, CACHING;
    }
}
