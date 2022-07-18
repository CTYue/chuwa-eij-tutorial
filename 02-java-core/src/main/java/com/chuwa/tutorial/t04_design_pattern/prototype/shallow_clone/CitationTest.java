package com.chuwa.tutorial.t04_design_pattern.prototype.shallow_clone;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 8:30 AM
 */
public class CitationTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 创建一个原型类对象
        Citation citation = new Citation();

        Student stu = new Student();
        stu.setName("Zhang San");
        citation.setStu(stu);

        Citation citation1 = citation.clone();
        citation1.getStu().setName("Li Si");

        citation.show();
        citation1.show();
    }
}
