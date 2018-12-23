package com.wx.mult.atomic1;

public class SimulationCAS {

    public static void main(String[] args) {
        IntegerOperation integerOperation = new IntegerOperation();
        for(int i = 0; i <10;i++) {
            new Thread(() -> {
                int a = integerOperation.incr();
                System.out.println(a);
            }).start();
        }

    }
}

class IntegerOperation {
    private int val = 0;
    public IntegerOperation() {
        compareAndSwap.compareAndSet(0, 0);
    }
    private CompareAndSwap compareAndSwap = new CompareAndSwap();
    public int incr() {
        int temp = val + 1;
        int value = compareAndSwap.getValue();//重要步骤，先获取一次内存的值，再进行操作
        if(compareAndSwap.compareAndSet(value, temp)) {
            val = temp;
        }
        return val;
    }

}

class CompareAndSwap {
    //内存值
    private int V = 0;

    public synchronized int  getValue() {
        return V;
    }

    public synchronized int compareAndSwap(int expectValue, int newValue) {
        int oldValue = V;

        if(V == expectValue) {
            V = newValue;
        }
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectValue, int newValue) {
        boolean result = expectValue == compareAndSwap(expectValue, newValue);
        System.out.println("reuslt = " + result);
        return result;
    }
}