package com.wx.mult;

/**
 * @author weixing
 * @date 2019/1/9
 **/
public class SimpleHappenBefore {
    private static int a = 0;
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Thread threadA = new ThreadA();
            Thread threadB = new ThreadB();
            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
            a = 0;
            flag = false;
        }
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            a = 1;
            flag = true;
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            if(flag) {
                a = a * 1;
            }
            if(a == 0) {
                System.out.println("a == 0");
            }
        }
    }
}
