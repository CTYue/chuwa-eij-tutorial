package com.chuwa.tutorial.t08_multithreading.c06_util_concurrent_locks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yanan Lyu
 * @date 3/29/22 9:27 AM
 * @description https://www.liaoxuefeng.com/wiki/1252599548343744/1306581060812834
 * interface	non-thread-safe	    thread-safe
 * List	        ArrayList	        CopyOnWriteArrayList
 * Map	        HashMap	            ConcurrentHashMap
 * Set	        HashSet/TreeSet	    CopyOnWriteArraySet
 * Queue	    ArrayDeque/LinkedList	ArrayBlockingQueue/LinkedBlockingQueue
 * Deque	    ArrayDeque/LinkedList	LinkedBlockingDeque
 *
 * 使用java.util.concurrent包提供的线程安全的并发集合可以大大简化多线程编程：
 * 多线程同时读写并发集合是安全的；
 * 尽量使用Java标准库提供的并发集合，避免自己编写同步代码。
 */
public class ConcurrentCollection {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>(16);
        // 在不同的线程读写:
        map.put("A", "1");
        map.put("B", "2");
        String a = map.get("A");

        /*
        但是它实际上是用一个包装类包装了非线程安全的Map，然后对所有读写方法都用synchronized加锁，
        这样获得的线程安全集合的性能比java.util.concurrent集合要低很多，所以不推荐使用。
         */
        Map<Object, Object> unsafeMap = new HashMap<>(16);
        Map<Object, Object> threadSafeMap = Collections.synchronizedMap(unsafeMap);
    }
}
