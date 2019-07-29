package com.dev_manager.service;

import com.dev_manager.basic.BasicSqlSupport;
import com.dev_manager.dao.UserMapper;
import com.dev_manager.entities.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;

@Singleton
@Service("UserService")
public class UserService {


    @Autowired
    BasicSqlSupport basicSqlSupport;
    /**
     * SqlSessionFactory 的声明周期应该是单例的，每个数据库应该对应一个
     */
    SqlSessionFactory sqlSessionFactory;

    private UserService() {
    }

    public boolean checkUser(String account, String password) {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = basicSqlSupport.getSqlSessionFactory();
        }
        //SqlSession 的生命周期应该是方法级别的，是线程不安全的，不然会浪费连接池资源
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = userMapper.check(account, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user != null;
    }


    public boolean addUser(User user) {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = basicSqlSupport.getSqlSessionFactory();
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            boolean validate = userMapper.checkAccountValidate(user.getAccount()) <= 0;
            if (!validate) {
                return false;
            }
            int id = userMapper.insertUser(user);
            return id > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }


}
