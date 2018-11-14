package com.me.mybatis.statementhandler;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author OuyangJie
 * @Date 2018/11/13 11:13
 * @Description:
 */
public class MyDefaultResultSetHandler implements MyResultSetHandler{

    @Override
    public <T> T handleResultSet(ResultSet rs, Class type) throws Exception {
        //创建一个空对象，再用反射赋值
        Object resultObj = new DefaultObjectFactory().create(type);
        if (rs.next()) {
            for (Field field : resultObj.getClass().getDeclaredFields()) {
                setValue(resultObj, field, rs);
            }
        }
        return (T) resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs) throws Exception {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field,rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        //bean属性的名字必须要和数据库column的名字一样
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        if(String.class == type){
            return rs.getString(field.getName());
        }
        return rs.getString(field.getName());
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
