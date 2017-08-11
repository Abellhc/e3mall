package cn.e3mall.Jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@SuppressWarnings("resource")
	@Test
	public void testJedis() throws Exception{
		//创建一个jedis对象。 需要指定服务端的IP和端口
		Jedis jedis = new Jedis("192.168.25.130", 6379);
		//使用jedis对象操作数据库， 每个redis命令都对应一个方法
		String string = jedis.set("mytest", "123");
		System.out.println(string);
		String string2 = jedis.get("mytest");
		//打印结果
		System.out.println(string2);
		//关闭jedis
		jedis.close();
		
	}
	
	@SuppressWarnings("resource")
	@Test
	public void testJedisPool() throws Exception{
		//创建一个jedispool对象， 需要指定服务端的IP和接口port
		JedisPool jedisPool = new JedisPool("192.168.25.130", 6379);
		//从jedispool中获得jedis对象
		Jedis jedis = jedisPool.getResource();
		//使用jedis操作redis服务器
		String string = jedis.get("mytest");
		//操作完毕后关闭jedis对象，连接池回收资源
		System.out.println(string);
		//关闭jedispool对象
		jedis.close();
		jedisPool.close();
	}
}
