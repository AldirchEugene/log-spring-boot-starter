package com.ucthings.log.utils;

import com.ucthings.log.entity.UcthingsLog;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @desc Redis工具类
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
public class UtilsRedis  {

    /**
     * redis模板
     */
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * redis存储的key
     */
    public static final String LOG_KEY = "ucthings-log";

    /**
     * 获取自身对象
     */
    private static com.ucthings.log.utils.UtilsRedis SELF_UTILS_REDIS;

    /**
     * 构造器
     * @param redisTemplate
     */
    public UtilsRedis(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 放入redis
     */
    public void action(){
        List<UcthingsLog> list = UtilsQueue.pop();
        if(list.size() > BigDecimal.ZERO.intValue()){
            leftPushAll(LOG_KEY,list);
        }
    }

    /**
     * 添加
     * @param key
     * @param value
     */
    public void leftPush(String key,Object value){
        redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 批量插入
     * @param key
     * @param value
     */
    public void leftPushAll(String key, Collection<UcthingsLog> value){
        for (UcthingsLog ucthingsLog : value) {
            leftPush(key,ucthingsLog);
        }
    }

    public static com.ucthings.log.utils.UtilsRedis getSelfUtilsRedis() {
        return SELF_UTILS_REDIS;
    }

    public static void setSelfUtilsRedis(com.ucthings.log.utils.UtilsRedis selfUtilsRedis) {
        SELF_UTILS_REDIS = selfUtilsRedis;
    }
}
