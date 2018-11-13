package com.me.mybatis.executor;

/**
 * @author OuyangJie
 * @Date 2018/11/13 14:33
 * @Description:
 */
public class MyExecutorFactory {
    public static final String SIMPLE = "simple";
    public static final String CACHING = "caching";

    public static MyExecutor getDefaultExecutor(){
        return getExecutor(SIMPLE);
    }

    public static MyExecutor getExecutor(String key) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new MySimpleExecutor();
        }
        if (CACHING.equalsIgnoreCase(key)) {
            return new MyCachingExecutor(new MySimpleExecutor());
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType{
        SIMPLE, CACHING;
    }
}
