package com.ucthings.log.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @desc 线程池工具类
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
public class UtilsThreadPool {

    /**
     * 创建线程池
     * @return
     */
    public static ThreadPoolExecutor getThreadPoolExecutor(){
        return new ThreadPoolExecutor(30,
                30,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100000),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
