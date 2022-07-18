package com.chuwa.tutorial.t04_design_pattern.singleton.destroysingleton;

import java.io.*;

/**
 * @author ylyu
 * @version 1.0
 * <p>
 * 破坏单例模式：序列化反序列化
 * @date 1/1/22 10:36 PM
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //往文件中写对象
//        writeObject2File();

        //从文件中读取对象
        readObjectFromFile();
        readObjectFromFile();
    }

    //从文件中读取对象
    public static void readObjectFromFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/home/ylyu/Downloads/a"));
        Singleton instance = (Singleton) ois.readObject();
        System.out.println(instance);
        ois.close();
    }

    //往文件中写对象
    public static void writeObject2File() throws IOException {
        Singleton instance = Singleton.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home/ylyu//Downloads/a"));
        oos.writeObject(instance);
        oos.close();
    }
}
