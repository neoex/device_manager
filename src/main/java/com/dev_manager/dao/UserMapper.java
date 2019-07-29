package com.dev_manager.dao;

import com.dev_manager.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 访问数据库，检查用户名和密码输入是否正确。
     *
     * @param username
     * @param password
     * @return
     */
    User check(@Param("username") String username, @Param("password") String password);

    /**
     * 访问User 检查账号是否已经被注册了
     *
     * @param account 要被注册的账号
     * @return int 返回此账号被注册的个数
     */
    int checkAccountValidate(@Param("account") String account);

    /**
     * 添加用户
     *
     * @param user user to insert
     * @return id of the inserted
     */
    int insertUser(User user);
}
