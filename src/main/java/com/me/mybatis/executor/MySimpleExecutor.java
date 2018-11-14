package com.me.mybatis.executor;

import com.me.mybatis.config.MyConfiguration;
import com.me.mybatis.config.MyMapperRegistry;
import com.me.mybatis.statementhandler.MyDefaultStatementHandler;
import com.me.mybatis.statementhandler.MyStatementHandler;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:45
 * @Description:
 */
public class MySimpleExecutor implements MyExecutor {
    private MyConfiguration myConfiguration;

    public MySimpleExecutor(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    @Override
    public <T> T doQuery(MyMapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        MyStatementHandler myStatementHandler = myConfiguration.newStatementHandler();
        return myStatementHandler.query(mapperData, parameter);
    }
}
