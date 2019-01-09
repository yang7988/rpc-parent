package com.rayvision.rpc.cache;

import com.rayvision.rpc.cache.enums.JedisStatus;
import com.rayvision.rpc.cache.key.RedisKey;
import com.rayvision.rpc.cache.serializer.Serializer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.nio.charset.Charset;

@Component
public class JedisTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(JedisTemplate.class);

    @Autowired
    private Serializer serializer;

    @Autowired
    private JedisPool jedisPool;

    private void closeJedis(Jedis jedis) {
        if (jedis == null) return;
        jedis.close();

    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    public JedisStatus set(RedisKey key, Object value) {
        JedisStatus jedisStatus;
        try {
            Jedis jedis = getJedis();
            jedis.set(key.getRedisKey().getBytes(Charset.defaultCharset()),serializer.serializer(value));
            closeJedis(jedis);
            jedisStatus = JedisStatus.OK;
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()) {
                LOGGER.error("JedisTemplate set({},{}) method called error \r\n{}"
                        ,key.getRedisKey(),value,ExceptionUtils.getStackTrace(e));
            }
            jedisStatus = JedisStatus.FAILD;
        }
        return jedisStatus;
    }

    public <T> T get(RedisKey key, Class<T> clazz) {
        T t = null;
        try {
            Jedis jedis = getJedis();
            byte[] bytes = jedis.get(key.getRedisKey().getBytes(Charset.defaultCharset()));
            if (bytes != null) {
                t = serializer.deserializer(bytes, clazz);
            }
            closeJedis(jedis);
        } catch (Exception e) {
            if(LOGGER.isErrorEnabled()) {
                LOGGER.error("JedisTemplate get({},{}) method called error \r\n{}"
                        ,key.getRedisKey(),clazz.getName(), ExceptionUtils.getStackTrace(e));
            }
        }
        return t;
    }


}