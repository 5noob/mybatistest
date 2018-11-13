package com.me.mybatis.dao;

import com.me.mybatis.annotation.SqlAnno;
import com.me.mybatis.domain.Test;

/**
 * @author OuyangJie
 * @Date 2018/11/9 17:51
 * @Description:
 */
public interface TestDAO {

    @SqlAnno("select * from test where id = ?")
    Test selectById(Integer id);

    default void testDefaultMethod(){
        System.out.println("===调用接口中的默认方法，用来验证MapperProxy中的isDefaultMethod方法===");
    }
}
