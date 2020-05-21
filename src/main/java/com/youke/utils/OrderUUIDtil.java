package com.youke.utils;

import java.util.Random;
import java.util.UUID;

public class OrderUUIDtil {
    public static String getOrderIdByUUId() {
        int first = new Random(10).nextInt(4);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%012d", hashCodeV);
    }
}
