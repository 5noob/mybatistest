package com.me.mybatis.executor;

import com.me.mybatis.config.MyMapperRegistry;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:11
 * @Description:
 */
public interface MyExecutor {

    /**
     * 查询
     * @param mapperData sql和返回值类型
     * @param parameter 参数
     * @param <T>
     * @return
     */
    <T> T doQuery(MyMapperRegistry.MapperData mapperData, Object parameter) throws Exception;
}
