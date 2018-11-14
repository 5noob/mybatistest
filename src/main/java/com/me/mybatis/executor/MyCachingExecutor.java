package com.me.mybatis.executor;

import com.me.mybatis.config.MyMapperRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author OuyangJie
 * @Date 2018/11/13 14:26
 * @Description:
 */
public class MyCachingExecutor implements MyExecutor{
    private MyExecutor delegate;

    private Map<String,Object> localCache = new HashMap<>();

    public MyCachingExecutor(MyExecutor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T doQuery(MyMapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        Object result = localCache.get(mapperData.getSql());
        if(null != result){
            System.out.println("缓存命中");
            return (T) result;
        }
        result = delegate.doQuery(mapperData, parameter);
        localCache.put(mapperData.getSql(), result);
        return (T) result;
    }
}
