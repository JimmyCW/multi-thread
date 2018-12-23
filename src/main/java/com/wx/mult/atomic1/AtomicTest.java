package com.wx.mult.atomic1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  原子性
 *   i++ 的原子性问题实际分为三步
 *   1. 读
 *   2. 改
 *   3。写
 *
 *   原子变量 AtomicInteger
 *   实现原理： 1.volatile 内存可见性
 *            2. CAS compare-and-swap 算法 来保证原子性 判断 + 交换
 *            CAS为硬件+软件的支持  是一种无锁 非阻塞算法的实现
 *            CAS包含三个操作数
 *            1.V 内存值
 *            2.A 预估值
 *            3.B 更新值
 *            当且仅当 V == A时  V = B, 否则不做任何操作
 *
 *
 *
 *
 */
public class AtomicTest {
    public static void main(String[] args) {
        AtomicThread atomicThread = new AtomicThread();
        for(int i = 0; i <  10; i++) {
            new Thread(atomicThread).start();
        }
    }

}

class AtomicThread implements Runnable {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        System.out.println(this.getValue());
    }

    public int getValue() {
        return atomicInteger.getAndIncrement();
    }
}