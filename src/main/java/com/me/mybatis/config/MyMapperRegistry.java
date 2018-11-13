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

    public static Map<String, MapperData> methodSqlMapping = new HashMap<>();


    public MyMapperRegistry() {
    }

    public void put(String key, MapperData mapperData){
        methodSqlMapping.put(key, mapperData);
    }

    public MapperData get(String key){
        return methodSqlMapping.get(key);
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
}
