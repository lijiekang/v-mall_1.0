package com.vmall.vutil;

import com.vmall.vutil.interfaces.IOFunction;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import java.io.ObjectOutputStream;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 20:33 2019-05-11
 * @Modifyied By:
 */
public class IOUtils {

    public static ObjectOutputStream getObjectOutputStream(ServletOutputStream servletOutputStream,IOFunction ioFunction) throws Exception{
        return ioFunction.generatorOutputStream(servletOutputStream);
    }
}
