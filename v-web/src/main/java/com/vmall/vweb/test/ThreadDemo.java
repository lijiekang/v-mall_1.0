package com.vmall.vweb.test;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 14:18 2019-05-12
 * @Modifyied By:
 */
public class ThreadDemo {

    int j=0;

    public void test(){
        Thread t1=new Thread(()->{while(true)j++;});
        Thread t2=new Thread(()->{while(true)j++;});
        Thread t3=new Thread(()->{while(true)j--;});
        Thread t4=new Thread(()->{while(true)j--;});

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
