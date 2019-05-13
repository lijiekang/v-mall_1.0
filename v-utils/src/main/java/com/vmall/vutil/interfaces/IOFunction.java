package com.vmall.vutil.interfaces;

import javax.servlet.ServletOutputStream;
import java.io.ObjectOutputStream;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 20:30 2019-05-11
 * @Modifyied By:
 */
public interface IOFunction {


    ObjectOutputStream generatorOutputStream(ServletOutputStream servletOutputStream) throws Exception;
}
