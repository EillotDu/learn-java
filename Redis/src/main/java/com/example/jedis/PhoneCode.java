package com.example.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class PhoneCode {

    public static Jedis getJedis() {
        return new Jedis("localhost", 6379);
    }

    // 1生成6位数字验证码
    public static String getCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code.append(rand);
        }
        return code.toString();

    }

    // 2 每个手机每天只能发送三次，验证码放到redis中，设置过期时间
    public static String getVerifyCode(String phone) {
        Jedis jedis = PhoneCode.getJedis();

        //拼接key
        //手机发送次数key
        String countKey = "VerifyCode" + phone + ":count";

        //验证码
        String codeKey = "VerifyCode" + phone + ":code";

        //每个手机每天只能发三次
        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey, 24 * 50 * 60, "1");
        } else if (Integer.parseInt(count) <= 2) {
            jedis.incr(countKey);
        } else if (Integer.parseInt(count) > 2) {
            System.out.println("今天发送次数超过三次");
            return null;
        }

        //发送的验证码放到redis里面
        String vcode = getCode();
        jedis.setex(codeKey, 120, vcode);
        return vcode;
    }

    //3 验证码校验
    public static void verifyCode(String phone, String code) {
        Jedis jedis = PhoneCode.getJedis();
        String codeKey = "VerifyCode" + phone + ":code";
        String redisCode = jedis.get(codeKey);
        if (redisCode.equals(code)) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
    }

    public static void main(String[] args) {

        String verifyCode = PhoneCode.getVerifyCode("133");
        System.out.println("verifyCode:  " + verifyCode);

        PhoneCode.verifyCode("133", "234234");

    }


}
