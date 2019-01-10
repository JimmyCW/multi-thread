package com.wx.mult;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author weixing
 * @date 2019/1/8
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Date(System.currentTimeMillis()));
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println("count = " + countDownLatch.getCount());
               countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(new Date(System.currentTimeMillis()));
    }
}
