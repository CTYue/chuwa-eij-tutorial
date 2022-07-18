package com.chuwa.tutorial.t04_design_pattern.prototype.deep_clone;

import java.io.Serializable;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 8:25 AM
 */
public class Citation implements Cloneable, Serializable {
    private Student stu;

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }

    @Override
    public String toString() {
        return "Citation{" +
                "stu=" + stu +
                '}';
    }

    public void show() {
        System.out.println(stu.getName() + ": get a citation in 2022");
    }
}
