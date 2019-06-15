package com.ryan.miaosha.redis;

import com.alibaba.fastjson.JSON;
import com.ryan.miaosha.redis.keys.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
    @Autowired
    JedisPool jedisPool;

    public <T> T get(KeyGenerator keyGenerator, String key, Class<T> tClass) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = keyGenerator.getKey(key);
            String str = jedis.get(key);
            T t = stringToBean(str, tClass);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> boolean set(KeyGenerator keyGenerator, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            key = keyGenerator.getKey(key);
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            if (keyGenerator.getExpireTimeOut() <= 0) {
                jedis.set(key, str);
            } else {
                jedis.setex(key, keyGenerator.getExpireTimeOut(), str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    public boolean exists(KeyGenerator keyGenerator, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(keyGenerator.getKey(key));
        } finally {
            jedis.close();
        }
    }

    public Long incr(KeyGenerator keyGenerator, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(keyGenerator.getKey(key));
        } finally {
            jedis.close();
        }
    }

    public Long decr(KeyGenerator keyGenerator, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(keyGenerator.getKey(key));
        } finally {
            jedis.close();
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        }
        return JSON.toJSONString(value);
    }

    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T)str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T)Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
