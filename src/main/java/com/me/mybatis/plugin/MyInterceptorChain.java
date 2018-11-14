package com.me.mybatis.plugin;

import org.apache.ibatis.plugin.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OuyangJie
 * @Date 2018/11/14 18:24
 * @Description:
 */
public class MyInterceptorChain {
    private List<Interceptor> interceptors = new ArrayList<>();

    /**
     * 在这里加入自定义的插件
     */
    public MyInterceptorChain() {
        interceptors.add(new MyFirstPlugin());
        interceptors.add(new MySecondPlugin());
    }

    public Object pluginAll(Object target){
        for(Interceptor interceptor: interceptors){
            target = interceptor.plugin(target);
        }
        return target;
    }
}
