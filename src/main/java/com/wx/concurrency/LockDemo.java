package com.wx.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weixing
 * @date 2019/1/10
 **/
public class LockDemo {

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>(20);
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Producer producer = new Producer(arr, 2, lock, condition);
        Consumer consumer = new Consumer(arr, lock, condition);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class Producer implements Runnable {

    private List<String> arr;

    private int size;

    private Lock lock;
    private Condition condition;
    public Producer(List<String> arr, int size, Lock lock, Condition condition) {
        this.arr = arr;
        this.size = size;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for(int i = 0; i < 10; i++) {
                while (arr.size() == size) {
                    condition.await();
                }
                arr.add("aa");
                System.out.println("producer = " + arr.size());
                condition.signal();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Consumer implements Runnable {
    private List<String> arr;

    private Lock lock;
    private Condition condition;
    public Consumer(List<String> arr, Lock lock, Condition condition) {
        this.arr = arr;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for(int i = 0; i < 10; i++) {
                while (arr.isEmpty()) {
                    condition.await();
                }
                String str = arr.get(arr.size() - 1);
                arr.remove(arr.size() - 1);
                System.out.println("取出的字符串 = " + str + " size = " + arr.size());
                condition.signal();
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }
}
