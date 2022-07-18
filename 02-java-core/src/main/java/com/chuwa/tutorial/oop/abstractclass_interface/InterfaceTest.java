package com.chuwa.tutorial.oop.abstractclass_interface;

/**
 * @author b1go
 * @date 6/10/22 4:02 PM
 */
public class InterfaceTest implements InterfaceA, InterfaceB{
    @Override
    public void printSomething() {

        //Option 1 重新写一个 -> Provide our own implementation.
        System.out.println("I am inside Main class");

        //Option 2 选一个父亲 -> Use existing implementation from interfaceA or interfaceB or both.
        InterfaceA.super.printSomething();
        InterfaceB.super.printSomething();
    }
}
