package com.kfit.mybatis;

//import com.dp.common.dao.DaoUtil;
import com.kfit.mybatis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tk.mybatis.mapper.model.TSysUpdateLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: DaleyZou
 * @Description: 用于向数据库中写入操作日志，十个数据写一条记录
 * @Date: Created in 14:56 2018/12/28
 * @Modified By:
 */
@Service
public class OperationLogSaver {

    protected static Logger logger = LoggerFactory.getLogger(OperationLogSaver.class);
//    @Autowired
//    private TSysUpdateLogMapper tSysUpdateLogMapper;
    @Autowired
    private DaoUtil daoUtil;
    private LinkedBlockingQueue<User> queue;

    private static int dbCacheSize = 1;
    private static Thread saverdbThread;

    public OperationLogSaver() {
        init();
    }
    public void putRecord(List<User> records){
        queue.addAll(records);
    }
    public void putRecord(User record){
        try {
            queue.put(record);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }
    }
    public void init() {
        queue = new LinkedBlockingQueue<User>();
        saverdbThread = new Thread("operationLog-Saver") {
            @Override
            public void run() {
                try {
                    while (true) {
                        if(null == queue || queue.isEmpty()){
                            Thread.sleep(500);
                            //System.out.println("sleep 500");
                            continue;
                        }
                        logger.info("总个数 "+ queue.size());
                        List<User> list = new ArrayList<User>();
                        queue.drainTo(list, dbCacheSize);
                        logger.info("处理的个数 "+ list.size());
                        if(null != list && list.size() > 0){
                            daoUtil.batchInsert(list);
                            Thread.sleep(3000);
                        }
                    }
                } catch (Exception t) {
                    logger.error("Unexpected exception on Thread %s!", t);
                }
            }
        };
        saverdbThread.start();
    }
}
