package cn.e3mall.JedisClient;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.jedis.JedisClientPool;

public class JedisClientTest {

	@Test
	public void testJedisClient() throws Exception{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
//		String result = jedisClient.get("mytestcluster");
		String result = jedisClient.get("mytest");
		
		System.out.println(result);
	}
}
