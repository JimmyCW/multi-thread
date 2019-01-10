package com.wx.concurrency;

import java.util.stream.IntStream;

/**
 * @author weixing
 * @date 2019/1/6
 **/
public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 100).forEach( i -> {
                System.out.println(Thread.currentThread().getName() + " " + i);
            });

        }, "t1");
        t1.start();
        IntStream.range(1, 100).forEach( i -> {
            System.out.println(Thread.currentThread().getName() + " " + i);
        });
        Thread.currentThread().join();
    }
}
