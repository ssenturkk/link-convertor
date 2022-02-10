package com.ssenturk.trendyollinkconvertor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class CacheConfig {

    @Autowired
    Environment environment;

    /** Set jedis configure setting */
    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(5);
        config.setMinIdle(2);
        config.setMaxIdle(3);

        JedisPool pool =  new JedisPool(config, environment.getProperty("trendyol.redis.host"),
                                        Integer.valueOf(environment.getProperty("trendyol.redis.port")), 1000);

        return pool;
    }
}