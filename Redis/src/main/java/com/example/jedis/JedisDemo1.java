package com.example.jedis;

import redis.clients.jedis.Jedis;

public class JedisDemo1 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

//        String value = jedis.ping();
//        System.out.println(value);
    }
}
