package com.kfit.mybatis;

import java.util.List;
import com.kfit.mybatis.dao.UserMapper;
import com.kfit.mybatis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DaoUtil {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    //@Autowired
   // private UserMapper userMapper;
    public final boolean batchInsert(final List<User> list) {
        boolean rt = true;
        int size = list.size();
        try {
            for (int i = 0; i < size; i++) {
                User paramObj = list.get(i);
                //userMapper.insert(paramObj);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            rt = false;
        }
        return rt;
    }
}