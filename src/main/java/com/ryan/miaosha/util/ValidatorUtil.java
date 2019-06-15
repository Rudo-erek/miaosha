package com.ryan.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(mobile);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("18362903235"));
        System.out.println(isMobile("1223232"));
    }
}
