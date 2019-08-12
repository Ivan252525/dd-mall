package com.dd.common.utils.string;

import java.util.Random;

/**
 * 字符串操作工具类
 *
 */
public class StringUtils {

    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence s) {
        return !isEmpty(s);
    }

    public static String random(int count) {
        RandomString gen = new RandomString(count, new Random());
        return gen.nextString();
    }

}
