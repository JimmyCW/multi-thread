package com.wx.concurrency.chap1;

import java.util.Arrays;

/**
 *
 * runnable设计类似 策略模式
 * @author weixing
 * @date 2019/1/1
 **/
public class BankCallSimulation {

    public static void main(String[] args) {
        BankWindow bankWindow = new BankWindow();
        Thread t1 = new Thread(bankWindow, "窗口1");
        Thread t2 = new Thread(bankWindow, "窗口2");
        Thread t3 = new Thread(bankWindow, "窗口3");
        t1.start();
        t2.start();
        t3.start();

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.activeCount());
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);

    }

}

class BankWindow implements Runnable {

    private static int number = 0;

    private static final int MAX = 50;

    @Override
    public void run() {
        while (number < MAX) {
            System.out.println(Thread.currentThread().getName() + " : 号码为 :" + number++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
