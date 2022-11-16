package com.ucthings.log.utils;

/**
 * @desc traceId的threadLocal
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
public class TraceIdThreadLocal {

    /**
     * traceId对象
     */
    private static ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    /**
     * 添加
     * @param val
     */
    public static void add(String val){
        TRACE_ID.set(val);
    }

    /**
     * 获取
     * @return
     */
    public static String get(){
        return TRACE_ID.get();
    }

    /**
     * 删除
     * @return
     */
    public static void remove(){
        TRACE_ID.remove();
    }
}
