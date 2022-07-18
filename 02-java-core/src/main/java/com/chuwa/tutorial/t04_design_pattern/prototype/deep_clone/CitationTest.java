package com.chuwa.tutorial.t04_design_pattern.prototype.deep_clone;

import java.io.*;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 8:30 AM
 */
public class CitationTest {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        // 创建一个原型类对象
        Citation citation = new Citation();

        Student stu = new Student();
        stu.setName("Zhag San");
        citation.setStu(stu);

        // 创建对象输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home/ylyu/Downloads/a.txt"));
        // 写对象
        oos.writeObject(citation);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/home/ylyu/Downloads/a.txt"));
        Citation citation1 = (Citation) ois.readObject();
        ois.close();

        citation1.getStu().setName("Li Si");

        citation.show();
        citation1.show();
    }
}
