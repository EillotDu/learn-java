package com.example.jedis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;


class JedisDemoTest {

    protected static Jedis jedis;

    @BeforeAll
    public static void init() {
        jedis = new Jedis("localhost", 6379);
    }

    @AfterAll
    public static void closeJedis() {
        jedis.close();
    }


    @Test
    public void demo1() {
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key + " ");
        }
    }

    @Test
    public void demo2() {
        jedis.set("name", "lucy");
        System.out.println(jedis.get("name"));
    }

    @Test
    public void demo3() {
        jedis.mset("k1", "v1", "k2", "v2");
        List<String> values = jedis.mget("k1", "k2");
        for (String value : values) {
            System.out.print(value + " ");
        }
    }

    @Test
    public void demo4() {
        jedis.rpush("key1", "lucy", "mary", "jack");
        List<String> values = jedis.lrange("key1", 0, -1);
        for (String value : values) {
            System.out.print(value + " ");
        }
    }

    @Test
    public void demo5() {
        jedis.sadd("names", "lucy", "jack");

        Set<String> values = jedis.smembers("names");
        for (String value : values) {
            System.out.print(value + " ");
        }
    }

    @Test
    public void demo6() {
        jedis.hset("users", "age", "20");
        System.out.println(jedis.hget("users", "age"));
    }

    @Test
    public void demo7() {
        jedis.zadd("china", 100d, "shanghai");
        System.out.println(jedis.zrange("china", 0, -1));
    }

}