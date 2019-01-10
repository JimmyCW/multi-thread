package com.wx.concurrency;

/**
 * @author weixing
 * @date 2019/1/6
 **/
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            Thread t1 = new Thread(() -> {
               while (true) {
                   System.out.println("t1 is running");
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            });
            t1.start();
            try {
                Thread.sleep(100000);
                System.out.println("t.ok");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        Thread.sleep(5000);
        t.start();
        System.out.println("main over");
    }
}
