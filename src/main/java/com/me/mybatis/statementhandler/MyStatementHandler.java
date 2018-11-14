package com.me.mybatis.statementhandler;

import com.me.mybatis.config.MyMapperRegistry;

/**
 * @author OuyangJie
 * @Date 2018/11/14 17:52
 * @Description:
 */
public interface MyStatementHandler {
    <T> T query(MyMapperRegistry.MapperData mapperData, Object param) throws Exception;
}
