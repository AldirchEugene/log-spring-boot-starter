package com.ucthings.log.entity;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @desc 定义通用日志对象
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
@Data
public class UcthingsLog {
    /**
     * 消息发起者创建一个traceId,伴随这个条消息的整个生命周期
     */
    private String traceId;

    /**
     * 日志级别
     */
    private String logLevel;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名称
     */
    private String threadName;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 时间：发起时间或接收时间
     * 2022-11-14 15:48:52
     */
    private LocalDateTime dateTime;

    /**
     * 动作：发起或接收
     */
    private String action;

    /**
     * 作者：发起者或接收者
     */
    private String author;

    /**
     * 作者的类型
     */
    private String authorType;

    /**
     * 地点：发起地点或接收地点
     */
    private String place;

    /**
     * 干什么（what）
     */
    private String operationType;

    /**
     * 干这个事情需要的数据
     */
    private String data;

    /**
     * 元数据
     */
    private String metaData;

    /**
     * 日志转换
     * @param iLoggingEvent
     * @return
     */
    public static UcthingsLog getInstance(final ILoggingEvent iLoggingEvent){
        // 时间戳
        Long timeStamp = iLoggingEvent.getTimeStamp();
        // 类名
        String className = iLoggingEvent.getLoggerName().toString();
        // 线程名
        String threadName = iLoggingEvent.getThreadName();
        // 格式化的数据
        String formattedMessage = iLoggingEvent.getFormattedMessage();
        JSONObject jsonObject = JSONObject.parseObject(formattedMessage);

        UcthingsLog ucthingsLog = new UcthingsLog();
        ucthingsLog.setTraceId(jsonObject.getString("traceId"));
        ucthingsLog.setLogLevel(iLoggingEvent.getLevel().levelStr);
        ucthingsLog.setClassName(className);
        ucthingsLog.setThreadName(threadName);
        ucthingsLog.setTimestamp(timeStamp);
        ucthingsLog.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp),ZoneId.systemDefault()));
        ucthingsLog.setAction(jsonObject.getString("action"));
        ucthingsLog.setAuthor(jsonObject.getString("author"));
        ucthingsLog.setAuthorType(jsonObject.getString("authorType"));
        ucthingsLog.setPlace(jsonObject.getString("place"));
        ucthingsLog.setOperationType(jsonObject.getString("operationType"));
        ucthingsLog.setData(jsonObject.getString("data"));
        ucthingsLog.setMetaData(jsonObject.getString("metaData"));

        return ucthingsLog;
    }
}
