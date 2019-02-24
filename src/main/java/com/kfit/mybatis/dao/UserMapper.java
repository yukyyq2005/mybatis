package com.kfit.mybatis.dao;

import com.kfit.mybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ：youq
 * @date ：Created in 2019/2/24 20:37
 * @modified By：
 */
@Mapper
public interface UserMapper {

    @Insert({
            "insert into users (id, NAME, ",
            "age)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{age,jdbcType=INTEGER})"
    })
    int insert(User record);

    User getUser(int id);
    //User updateUser(int id);

    int updateUser(User record);

    int deleteUser(int id);
}
