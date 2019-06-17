package com.ryan.miaosha.redis.keys;

public class MiaoShaUserKeyGenerator extends AbstractKeyGenerator {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public MiaoShaUserKeyGenerator(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static final MiaoShaUserKeyGenerator keyByTK = new MiaoShaUserKeyGenerator(TOKEN_EXPIRE, "tk");
}
