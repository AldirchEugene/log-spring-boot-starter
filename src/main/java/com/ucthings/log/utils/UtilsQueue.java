package com.ucthings.log.utils;

import com.ucthings.log.entity.UcthingsLog;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @desc 队列工具类
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
@Slf4j
public class UtilsQueue {

    /**
     * 一次从queue中获取50000条日志
     */
    public static final int MAX_ELEMENT = 50000;

    /**
     * 阻塞队列最大容量100000
     */
    private static final BlockingDeque<UcthingsLog> queue = new LinkedBlockingDeque<UcthingsLog>(100000);

    /**
     * 往队列添加元素
     */
    public static void add(UcthingsLog ucthingsLog){
        try {
            queue.add(ucthingsLog);
        }catch (IllegalStateException e){
            // 如果队列满了: 清空队列
            log.error("Queue log full need clean.");
            queue.clear();
        }
    }

    /**
     * 从队列获取
     * @return
     */
    public static List<UcthingsLog> pop(){
        LinkedList<UcthingsLog> list = new LinkedList<>();
        queue.drainTo(list,MAX_ELEMENT);
        return list;
    }
}
