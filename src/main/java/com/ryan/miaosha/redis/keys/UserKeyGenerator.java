package com.ryan.miaosha.redis.keys;

public class UserKeyGenerator extends AbstractKeyGenerator {
    public UserKeyGenerator(String prefix) {
        super(prefix);
    }

    public static UserKeyGenerator userKeyById = new UserKeyGenerator("Id");

    public static UserKeyGenerator userKeyByName = new UserKeyGenerator("name");
}
