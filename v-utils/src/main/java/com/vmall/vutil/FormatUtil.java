package com.vmall.vutil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转格式工具类
 */
public class FormatUtil {

    private static final SimpleDateFormat DF=new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 将格式转为yyyy-MM-dd
     * @param date
     * @return
     */
    public static String transferDate(Date date){
        return DF.format(date);
    }
}
