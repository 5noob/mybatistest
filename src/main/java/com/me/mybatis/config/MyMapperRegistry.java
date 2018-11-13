package com.me.mybatis.config;

import com.me.mybatis.domain.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author OuyangJie
 * @Date 2018/11/13 10:57
 * @Description:
 */
public class MyMapperRegistry {

    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    /**
     * 使用
     * 1. 在这里配置
     * 2. Java Bean的属性名字要和数据库表的名字一致
     */

    public MyMapperRegistry() {
        methodSqlMapping.put("com.me.mybatis.dao.TestDAO.selectById",
                new MapperData("select * from test where id = %d", Test.class));
    }

    public class MapperData<T>{
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }

    public MapperData get(String namespace){
        return methodSqlMapping.get(namespace);
    }
}
