package com.me.mybatis;

import com.me.mybatis.config.MyConfiguration;
import com.me.mybatis.dao.TestDAO;
import com.me.mybatis.domain.Test;
import com.me.mybatis.sqlsession.MySqlSession;

/**
 * @author OuyangJie
 * @Date 2018/11/9 17:51
 * @Description:
 */
public class Main {

    public static MySqlSession getSqlSession() {
        MyConfiguration myConfiguration = new MyConfiguration();
        myConfiguration.scanPath("com.me.mybatis.dao");
        return new MySqlSession(myConfiguration);
    }

    public static void main(String[] args) {
        TestDAO testDAO = getSqlSession().getMapper(TestDAO.class);
        Test test = testDAO.selectById(1);
        System.out.println(test);
    }
}
