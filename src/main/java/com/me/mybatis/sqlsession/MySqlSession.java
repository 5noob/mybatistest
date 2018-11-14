package com.me.mybatis.sqlsession;

import com.me.mybatis.config.MyConfiguration;
import com.me.mybatis.config.MyMapperRegistry;
import com.me.mybatis.executor.MyExecutor;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:09
 * @Description:
 */
public class MySqlSession {
    private MyConfiguration myConfiguration;

    private MyExecutor myExecutor;

    public MySqlSession(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
        this.myExecutor = myConfiguration.newExecutor();
    }

    public <T> T getMapper(Class<T> type) {
        return myConfiguration.getMapper(type, this);
    }

    public <T> T selectOne(MyMapperRegistry.MapperData mapperData, Object parameter) throws Exception {
        return myExecutor.doQuery(mapperData, parameter);
    }

    public MyConfiguration getMyConfiguration() {
        return myConfiguration;
    }
}
