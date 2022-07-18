package com.chuwa.tutorial.t04_design_pattern.prototype.demo;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 8:11 AM
 */
public class RealizeType implements Cloneable {

    /*
    浅克隆：创建一个新对象，新对象的属性和原来对象完全相同，对于非基本类型属性，仍指向原
        有属性所指向的对象的内存地址。
    深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
     */
    public RealizeType() {
        System.out.println("object is created");
    }

    @Override
    protected RealizeType clone() throws CloneNotSupportedException {
        System.out.println("copy a new object of prototype");
        return (RealizeType) super.clone();
    }
}
