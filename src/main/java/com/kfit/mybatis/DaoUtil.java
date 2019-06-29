package com.kfit.mybatis;

import java.util.List;

import com.kfit.mybatis.dao.UserMapper;
import com.kfit.mybatis.domain.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Repository("daoUtil")
@Component
public class DaoUtil {
    //private Logger log = LoggerFactory.getLogger(getClass());
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private UserMapper userMapper;

    public final <O> boolean batchInsert(String sqlMapNameSpace,String statementName, final List<O> list) {
        boolean rt = true;
        // 此代码没有使用spring的事务,改动手动控制,如果和原spring事务一起使用,将无法回滚,必须注意,最好单独使用;
        //SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        int size = list.size();
        try {
            for (int i = 0; i < size; i++) {
                User paramObj = (User)list.get(i);
                userMapper.insert(paramObj);
//                sqlSession.insert(sqlMapNameSpace + "." + statementName, paramObj);
//                if ((i + 1) % 200 == 0 || (i + 1) == (size)) {
//                    // 手动每200条提交一次,提交后无法回滚
//                    sqlSession.commit();
//                    // 清理缓存，防止溢出
//                    sqlSession.clearCache();
//                }
            }
        } catch (Exception e) {
            // 没有提交的数据可以回滚
//            sqlSession.rollback();
            logger.error(e.getMessage(),e);
            rt = false;
        } finally {
//            sqlSession.close();
        }
        return rt;
    }
}