package cn.e3mall.JedisCluster;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterTest {

	@SuppressWarnings("resource")
	@Test
	public void testJedisCluster() throws Exception{
		//使用jediscluster对象。需要一个set<hostandport>参数， redis节点的列表
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.130", 7001));
		nodes.add(new HostAndPort("192.168.25.130", 7002));
		nodes.add(new HostAndPort("192.168.25.130", 7003));
		nodes.add(new HostAndPort("192.168.25.130", 7004));
		nodes.add(new HostAndPort("192.168.25.130", 7005));
		nodes.add(new HostAndPort("192.168.25.130", 7006));
		//直接使用jediscluster对象操作redis 在系统中单例存在
		JedisCluster jedisCluster = new JedisCluster(nodes);
		String string = jedisCluster.set("mytestcluster", "asd");
		System.out.println(string);
		String string2 = jedisCluster.get("mytestcluster");
		//打印结果
		System.out.println(string2);
		//系统关闭前， 关闭jediscluster对象
		jedisCluster.close();
		
	}
	
}
