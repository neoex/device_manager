package com.dev_manager.basic;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;


@Singleton
public class BasicSqlSupport {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    protected BasicSqlSupport() {
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }


}
