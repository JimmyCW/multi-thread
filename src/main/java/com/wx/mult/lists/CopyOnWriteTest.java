package com.wx.mult.lists;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * CopyOnWriteArrayList
 *  一般情况下，当多线程操作同一个list的时候，会报多线程操作异常
 *  但是用CopyOnWriteArrayList操作就不会
 *  因为操作时，会复制一个新的list
 *  但是这样操作就会效率比较低
 *  所以此类适合迭代多，但是插入删除操作少的情况下使用
 *
 *
 *
 */
public class CopyOnWriteTest {
    public static void main(String[] args) {
        ListThread listThread = new ListThread();
        for(int i = 0; i<10; i++) {
            new Thread(listThread).start();
        }

    }

}

class ListThread implements Runnable {
    private static List<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("A");
        list.add("B");
        list.add("C");
    }

    @Override
    public void run() {

        Iterator<String> iterable = list.iterator();
        while (iterable.hasNext()) {
            System.out.println(iterable.next());
            list.add("A");
        }
    }
}
