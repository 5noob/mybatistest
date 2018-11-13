package com.me.mybatis.sqlsession;

import com.me.mybatis.config.MyConfiguration;
import com.me.mybatis.executor.MyExecutor;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:09
 * @Description:
 */
public class MySqlSession {
    private MyConfiguration myConfiguration;

    private MyExecutor myExecutor;

    public MySqlSession(MyConfiguration myConfiguration, MyExecutor myExecutor) {
        this.myConfiguration = myConfiguration;
        this.myExecutor = myExecutor;
    }

    public <T> T getMapper(Class<T> type){
        return myConfiguration.getMapper(type, this);
    }

    public <T> T selectOne(String statement, String parameter){
        return myExecutor.query(statement, parameter);
    }
}
