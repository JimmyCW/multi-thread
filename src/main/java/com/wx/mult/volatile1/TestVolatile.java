package com.wx.mult.volatile1;

/**
 * volatile 关键字学习
 */
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            if(td.getFlag()) {
                System.out.println("flag is true");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {

    private  volatile ThreadLocal<Boolean> flag = new ThreadLocal<>();
    public ThreadDemo() {
        flag.set(false);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        flag.set(true);
        System.out.println("当前线程属性 flag:" + flag.get());
    }

    public boolean getFlag() {
        return flag.get().booleanValue();
    }
}
