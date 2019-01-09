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
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.set(key.getRedisKey().getBytes(Charset.defaultCharset()), serializer.serializer(value));
            jedisStatus = JedisStatus.OK;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JedisTemplate set({},{}) method called error {}"
                        , key.getRedisKey(), value, ExceptionUtils.getStackTrace(e));
            }
            jedisStatus = JedisStatus.FAILD;
        } finally {
            this.closeJedis(jedis);
        }
        return jedisStatus;
    }

    public JedisStatus setex(RedisKey key, int expire, Object value) {
        JedisStatus jedisStatus;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            jedis.setex(key.getRedisKey().getBytes(Charset.defaultCharset()), expire, serializer.serializer(value));
            jedisStatus = JedisStatus.OK;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JedisTemplate set({},{},{}) method called error {}"
                        , key.getRedisKey(), expire, value, ExceptionUtils.getStackTrace(e));
            }
            jedisStatus = JedisStatus.FAILD;
        } finally {
            this.closeJedis(jedis);
        }
        return jedisStatus;
    }

    public <T> T get(RedisKey key, Class<T> clazz) {
        T t = null;
        Jedis jedis = null;
        try {
            jedis = this.getJedis();
            byte[] bytes = jedis.get(key.getRedisKey().getBytes(Charset.defaultCharset()));
            t = serializer.deserializer(bytes, clazz);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("JedisTemplate get({},{}) method called error {}"
                        , key.getRedisKey(), clazz.getName(), ExceptionUtils.getStackTrace(e));
            }
        } finally {
            this.closeJedis(jedis);
        }
        return t;
    }


}