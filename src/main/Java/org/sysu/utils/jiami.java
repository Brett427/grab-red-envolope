package org.sysu.utils;

import org.springframework.util.DigestUtils;

public class jiami {

    private static final String slat = "32gwrgq23&^&*^&*&%875";

    public  static  String getSlat() {
        return slat;
    }

    public static String getMD5(Long seckillid)
    {
        String base = seckillid + "/"+ getSlat();
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
