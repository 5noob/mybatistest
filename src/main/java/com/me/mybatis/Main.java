package com.me.mybatis;

import com.me.mybatis.dao.TestDAO;
import com.me.mybatis.domain.Test;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author OuyangJie
 * @Date 2018/11/9 17:51
 * @Description:
 */
public class Main {

    public static SqlSession getSqlSession() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws IOException {
        TestDAO testDAO = getSqlSession().getMapper(TestDAO.class);
        Test test = testDAO.selectById(1);

//        testDAO.testDefaultMethod();

        //类文件是缓存在java虚拟机中，我们将类文件打印到文件中，便于查看
//        generateProxyFile("F:/TestDAOProxy.class");
    }

    private static void generateProxyFile(String path){
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", new Class<?>[]{TestDAO.class});

        try(FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            System.out.println("写文件错误");
        }
    }
}
