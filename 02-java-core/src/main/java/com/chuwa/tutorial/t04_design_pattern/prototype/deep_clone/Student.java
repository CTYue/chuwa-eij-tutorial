package com.chuwa.tutorial.t04_design_pattern.prototype.deep_clone;

import java.io.Serializable;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 8:35 AM
 */
public class Student implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
