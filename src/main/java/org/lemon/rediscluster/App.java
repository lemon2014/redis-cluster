package org.lemon.rediscluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Set<HostAndPort> jedisclusterNode = new HashSet<HostAndPort>();
        jedisclusterNode.add(new HostAndPort("192.168.142.128", 8001));
        jedisclusterNode.add(new HostAndPort("192.168.142.129", 8002));
        jedisclusterNode.add(new HostAndPort("192.168.142.130", 8003));
        jedisclusterNode.add(new HostAndPort("192.168.142.128", 8004));
        jedisclusterNode.add(new HostAndPort("192.168.142.129", 8805));
        jedisclusterNode.add(new HostAndPort("192.168.142.130", 8006));
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(19);
        config.setTestOnBorrow(true);

        //connectionTimeout:指的是连接一个ur1的连接等待时间
        //soTimeout:指的是连接上一个url,获取response的返回等待时间
        JedisCluster jediscluster = new JedisCluster(jedisclusterNode, 6000, 5000, 10, config);
        System.out.println(jediscluster.set("student", "zhuge"));
        System.out.println(jediscluster.set("age", "19"));
        System.out.println(jediscluster.get("student"));
        System.out.println(jediscluster.get("age"));
        System.out.println(jediscluster.get("name"));
        jediscluster.close();
    }
}
