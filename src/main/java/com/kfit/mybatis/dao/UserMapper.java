package com.kfit.mybatis.dao;

import com.kfit.mybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
}