package com.ssenturk.trendyollinkconvertor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** This class is the class where redis operations are defined*/
@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    public String get(String key) {
        Jedis jedis = null;
        try {
           jedis = jedisPool.getResource();
           return jedis.get(md5(key));
        }finally {
            jedis.close();
        }
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(md5(key), value);
        }finally {
            jedis.close();
        }

    }

    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(md5(key));
        }finally {
            jedis.close();
        }
    }

    public String md5(String link) {
        try {

            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(link.getBytes());
            byte messageDigest[] = digest.digest();


            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
