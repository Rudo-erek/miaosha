package com.ryan.miaosha.redis.keys;

public abstract class AbstractKeyGenerator implements KeyGenerator {

    private int seconds;
    private String prefix;

    public AbstractKeyGenerator(String prefix) {
        this(0, prefix);
    }

    public AbstractKeyGenerator(int seconds, String prefix) {
        this.prefix = prefix;
        this.seconds = seconds;
    }

    @Override
    public int getExpireTimeOut() {
        return this.seconds;
    }

    @Override
    public String getKey(String key) {
        return getClass().getSimpleName() + ":" + prefix + ":" + key;
    }
}
