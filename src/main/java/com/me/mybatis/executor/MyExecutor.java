package com.me.mybatis.executor;

/**
 * @author OuyangJie
 * @Date 2018/11/13 9:11
 * @Description:
 */
public interface MyExecutor {

    /**
     * 查询
     * @param statement sql
     * @param parameter 参数
     * @param <T>
     * @return
     */
    <T> T query(String statement, String parameter);
}
