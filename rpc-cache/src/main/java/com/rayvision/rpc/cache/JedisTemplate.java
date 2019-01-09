package com.rayvision.rpc.cache;

import com.rayvision.rpc.cache.key.RedisKey;
import com.rayvision.rpc.cache.serializer.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.nio.charset.Charset;

@Component
public class JedisTemplate {
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

    public String set(RedisKey key, Object value) {
        Jedis jedis = getJedis();
        String response = jedis.set(key.getRedisKey().getBytes(Charset.defaultCharset()),serializer.serializer(value));
        closeJedis(jedis);
        return response;
    }

    public <T> T get(RedisKey key, Class<T> clazz) {
        T t = null;
        Jedis jedis = getJedis();
        byte[] bytes = jedis.get(key.getRedisKey().getBytes(Charset.defaultCharset()));
        if (bytes != null) {
            t = serializer.deserializer(bytes, clazz);
        }
        closeJedis(jedis);
        return t;
    }


}