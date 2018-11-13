package com.me.mybatis.executor;

import com.me.mybatis.config.MyMapperRegistry;
import com.me.mybatis.statementhandler.MyStatementHandler;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:45
 * @Description:
 */
public class MySimpleExecutor implements MyExecutor {
    @Override
    public <T> T query(MyMapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        MyStatementHandler myStatementHandler = new MyStatementHandler();
        return myStatementHandler.query(mapperData, parameter);
    }
}
