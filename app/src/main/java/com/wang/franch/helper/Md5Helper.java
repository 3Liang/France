package com.wang.franch.helper;

import java.security.MessageDigest;

/**
 * Md5加密帮助类
 */
public class Md5Helper {

    public static String MD5(String text) {
        char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] bytes = text.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] hash = messageDigest.digest();
            int hashLength = hash.length;
            char[] charArr = new char[hashLength * 2];
            int var7 = 0;
            for (byte value : hash) {
                charArr[var7++] = chars[value >>> 4 & 15];
                charArr[var7++] = chars[value & 15];
            }
            return new String(charArr);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
