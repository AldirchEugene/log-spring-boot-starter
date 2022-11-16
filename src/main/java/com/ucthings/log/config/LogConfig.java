package com.ucthings.log.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.ucthings.log.filter.TraceIdFilter;
import com.ucthings.log.utils.UtilsRedis;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.util.Collections;

/**
 * @desc 日志配置类
 * @author Aldrich Eugene
 * @since 2022-11-14
 */
@Configuration
public class LogConfig {

    /**
     * 配置redis序列化bean对象
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        // key序列化
        template.setKeySerializer(new StringRedisSerializer());
        //value 序列化
        GenericFastJsonRedisSerializer redisSerializer = new GenericFastJsonRedisSerializer();
        template.setValueSerializer(redisSerializer);
        template.setHashKeySerializer(redisSerializer);
        template.setConnectionFactory(factory);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 配置utilsRedis的bean对象
     * @param redisTemplate
     * @return
     */
    @Bean
    public UtilsRedis utilsRedis(RedisTemplate<String,Object> redisTemplate){
        UtilsRedis utilsRedis = new UtilsRedis(redisTemplate);
        UtilsRedis.setSelfUtilsRedis(utilsRedis);
        return utilsRedis;
    }

    /**
     * 配置traceIdFilter的bean对象
     * @return
     */
    public FilterRegistrationBean<TraceIdFilter> traceIdFilterBean(){
        FilterRegistrationBean<TraceIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceIdFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        registrationBean.setOrder(0);
        return registrationBean;
    }
}
