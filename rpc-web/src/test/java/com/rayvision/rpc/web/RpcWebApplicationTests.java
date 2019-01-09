package com.rayvision.rpc.web;

import com.rayvision.rpc.cache.JedisTemplate;
import com.rayvision.rpc.cache.key.RedisKey;
import com.rayvision.rpc.cache.key.RedisKeysPrefix;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RpcWebApplication.class)
public class RpcWebApplicationTests {

	@Autowired
	private JedisTemplate jedisTemplate;

	@Test
	public void testJedis() throws Exception {
		String key = System.currentTimeMillis() + "";
		RedisKey redisKey = new RedisKey(RedisKeysPrefix.USER_KEY, key);
		String resp = jedisTemplate.set(redisKey, "hello world");
		System.out.println(resp);
	}

}
