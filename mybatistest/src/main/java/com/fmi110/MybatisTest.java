package com.fmi110;

import com.fmi110.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testInsert() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession        session = factory.openSession();

        User user = new User("fmi110"," 男",28);

        int id = session.insert("com.fmi110.domain.UserMapper.save", user);

//        logger.error("============={}",user);

        System.out.println("id = "+user.getId());

        session.commit();
        session.close();
    }
}
