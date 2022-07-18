package com.chuwa.tutorial.t04_design_pattern.factory.config_factory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/2/22 4:32 PM
 */
public class CoffeeFactory {

    // 加载配置文件，获取配置文件中配置的全类名，并船舰该类的对象进行存 储
    // 1, 定义容器对象存储咖啡对象
    private static final HashMap<String, Coffee> map = new HashMap<>();

    /*
    静态成员变量用来存储创建的对象（键存储的是名称，值存储的是对应的对象），而读取配置文件以及
    创建对象写在静态代码块中，目的就是只需要执行一次。
     */
    // 2, 加载配置文件，只需加载一次
    static {
        // 2.1 创建Properties 对象
        Properties p = new Properties();
        // 2.2 调用P对象中的load的方法进行配置文件的加载
        InputStream is = CoffeeFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            p.load(is);
            // 从p集合中获全类名并创建对象
            Set<Object> keys = p.keySet();
            for (Object key : keys) {
                String className = p.getProperty((String) key);
                // 通过反射技术创建对象
                Class<?> clazz = Class.forName(className);
                Coffee coffee = (Coffee) clazz.getDeclaredConstructor().newInstance();
                // 将名称和对象存储到容器中
                map.put((String) key, coffee);
            }
        } catch (ClassNotFoundException | IOException | NoSuchMethodException | InvocationTargetException
                | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    // 根据名称获得对象
    public static Coffee createCoffee(String name) {
        return map.get(name);
    }
}
