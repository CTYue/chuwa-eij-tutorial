package com.chuwa.tutorial.t04_design_pattern.singleton.destroysingletonwithreflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/1/22 10:58 PM
 */
public class Client {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        // 1, 获取Singleton类的字节码对象
        Class<Singleton> clazz = Singleton.class;
        //2, 获取Singleton类的私有无参构造方法对象
        Constructor<Singleton> constructor = clazz.getDeclaredConstructor();
        // 3, 取消访问检查
        constructor.setAccessible(true);

        //创建Singleton类的对象s1, s2
        Object s1 = constructor.newInstance();
        Object s2 = constructor.newInstance();

        // 判断通过反射创建的两个Singleton对象是否是同一个对象
        System.out.println(s1 == s2);
    }
}
