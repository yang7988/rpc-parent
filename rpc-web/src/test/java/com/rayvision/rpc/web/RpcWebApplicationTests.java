package com.rayvision.rpc.web;

import com.rayvision.rpc.cache.JedisTemplate;
import com.rayvision.rpc.cache.enums.JedisStatus;
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
		String key = "hello";
		RedisKey redisKey = new RedisKey(RedisKeysPrefix.USER_KEY, key);
//		JedisStatus status = jedisTemplate.setex(redisKey, 100,"hello world");
		JedisStatus status = jedisTemplate.set(redisKey, 123);
		int a = jedisTemplate.get(redisKey, int.class);
		System.out.println(a);
	}

}
