package com.me.mybatis.util;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author OuyangJie
 * @Date 2018/11/13 16:00
 * @Description:
 */
public class PackageUtilTest {

    @Test
    public void getClasses() {
        Set<Class<?>> classSet = PackageUtil.getClasses("com.me.mybatis.dao");
    }
}