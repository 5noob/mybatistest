package com.me.mybatis;

import com.me.mybatis.config.MyConfiguration;
import com.me.mybatis.dao.TestDAO;
import com.me.mybatis.domain.Test;
import com.me.mybatis.executor.MySimpleExecutor;
import com.me.mybatis.sqlsession.MySqlSession;

import java.io.IOException;

/**
 * @author OuyangJie
 * @Date 2018/11/9 17:51
 * @Description:
 */
public class Main {

    public static MySqlSession getSqlSession() throws IOException {
        return new MySqlSession(new MyConfiguration(), new MySimpleExecutor());
    }

    public static void main(String[] args) throws IOException {
        TestDAO testDAO = getSqlSession().getMapper(TestDAO.class);
        Test test = testDAO.selectById(1);
        System.out.println(test);
    }
}
