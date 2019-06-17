package com.ryan.miaosha.util;

import java.util.UUID;

public class UUIDUtis {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
