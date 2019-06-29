package com.kfit.mybatis;

//import com.dp.common.dao.DaoUtil;
import com.kfit.mybatis.dao.UserMapper;
import com.kfit.mybatis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tk.mybatis.mapper.model.TSysUpdateLog;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * @Author: DaleyZou
 * @Description: 用于向数据库中写入操作日志，十个数据写一条记录
 * @Date: Created in 14:56 2018/12/28
 * @Modified By:
 */
//@Service
public class OperationLogSaver {
    protected static Logger logger = LoggerFactory.getLogger(OperationLogSaver.class);
//    @Autowired
//    private DaoUtil daoUtil;
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
//                while (true) {
                    try {
//                        if (null == queue || queue.isEmpty()) {
//                            Thread.sleep(500);
//                            System.out.println("sleep 500 ");
//                            continue;
//                        }
                        List<User> list = new ArrayList<User>();
                        System.out.println("中华人民共和国 ");
                        queue.drainTo(list, dbCacheSize);
                        if (null != list && list.size() > 0) {
                            DaoUtil dao = new DaoUtil();
                            dao.batchInsert(list);
                            Thread.sleep(1000);
                        }
                        //int i = 1 / 0;
                    } catch (Exception t) {
                        logger.error("Unexpected exception on Thread %s!", t);
                    }
                }
//            }
        };
        saverdbThread.start();
    }
}
