package com.ucthings.log.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.ucthings.log.entity.UcthingsLog;
import com.ucthings.log.utils.UtilsQueue;
import com.ucthings.log.utils.UtilsRedis;
import com.ucthings.log.utils.UtilsThreadPool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @desc 自定义redisAppender读取log日志
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
public class RedisAppender extends AbstractAppender {

    ThreadPoolExecutor threadPoolExecutor = UtilsThreadPool.getThreadPoolExecutor();

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        UcthingsLog ucthingsLog = UcthingsLog.getInstance(iLoggingEvent);
        UtilsQueue.add(ucthingsLog);
        UtilsRedis selfUtilsRedis = UtilsRedis.getSelfUtilsRedis();
        if(selfUtilsRedis != null){
            threadPoolExecutor.execute(() -> selfUtilsRedis.action());
        }
    }
}
