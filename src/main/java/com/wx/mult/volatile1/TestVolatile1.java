package com.wx.mult.volatile1;

/**
 * volatile 关键字学习
 * 当多个线程操作共享数据时，内存是可以见的  可以理解为操作在主存中完成的

    1. 但volatil 无法替代synchronized
    因为不具有互斥性
    而且volatile不能保证原子性
 */
public class TestVolatile1 {

    public static void main(String[] args) {
        ThreadDemo1 td = new ThreadDemo1();
        new Thread(td).start();
        while (true) {
//            synchronized (td) {//使用同步锁可以刷新内存
                if(td.isFlag()) {
                    System.out.println("flag is true");
                    break;
                }
//            }
        }
    }
}

class ThreadDemo1 implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        flag = true;
        System.out.println("当前线程属性 flag:" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }
}

