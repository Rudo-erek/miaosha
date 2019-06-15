package com.ryan.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public final static String salt = "1a2b3c4d";

    public static String MD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    private static String inputPassToFormPass(String inputPass) {
        inputPass = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(3) + salt.charAt(5);
        System.out.println(inputPass);
        return MD5(inputPass);
    }

    private static String formPassToDBPass(String formPass, String salt) {
        return MD5(formPass + salt);
    }

    public static String encryptPass(String pass, String salt) {
        String formPass = inputPassToFormPass(pass);
        System.out.println(formPass);
        return formPassToDBPass(formPass, salt);
    }

    public static void main(String[] args) {
        System.out.println(encryptPass("123456", "!@#$dff"));
        System.out.println('A' - 'A');
        System.out.println((int)'z' - 'A');
    }
}
