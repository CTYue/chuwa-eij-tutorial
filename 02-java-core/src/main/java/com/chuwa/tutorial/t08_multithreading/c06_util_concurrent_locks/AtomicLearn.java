package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author b1go
 * @date 3/29/22 9:34 AM
 * @description 自己利用CAS实现
 */
public class AtomicLearn {
    public static int incrementAndGet(AtomicInteger var) {
        int prev, next;
        do {
            prev = var.get();
            next = prev + 1;
            //Keep looping until actual value equals to expected value.
            //which means latest var.get() == current var.get()
            //return false if actual value is NOT equal to expected value
        } while ( ! var.compareAndSet(prev, next));
        return next;
    }
}

/**
 * @description 直接使用现有的
 */
class IdGenerator {
    AtomicLong var = new AtomicLong(0);

    public long getNextId() {
        return var.incrementAndGet();
    }
}
