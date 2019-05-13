package com.vmall.vweb.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 14:15 2019-05-12
 * @Modifyied By:
 */
public class SocketDemo {

    public void getChar(){
        try {
            Socket socket=new Socket("localhost",19132);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message=bufferedReader.readLine();
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
