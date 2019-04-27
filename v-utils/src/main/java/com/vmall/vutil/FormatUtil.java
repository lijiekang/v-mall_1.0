package com.vmall.vutil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转格式工具类
 */
public class FormatUtil {

    public static final SimpleDateFormat DF=new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat DF_HMS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 将格式转为yyyy-MM-dd
     * @param date
     * @return
     */
    public static String transferDate(Date date){
        return DF.format(date);
    }
}
