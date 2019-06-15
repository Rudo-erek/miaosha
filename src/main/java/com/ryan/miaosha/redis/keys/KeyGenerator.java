package com.ryan.miaosha.redis.keys;

public interface KeyGenerator {

    int getExpireTimeOut();

    String getKey(String key);
}
