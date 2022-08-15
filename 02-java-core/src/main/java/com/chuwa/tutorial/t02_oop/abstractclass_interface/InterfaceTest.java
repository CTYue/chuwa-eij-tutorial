package com.chuwa.tutorial.t02_oop.abstractclass_interface;

/**
 * @author b1go
 * @date 6/10/22 4:02 PM
 */
public class InterfaceTest implements InterfaceB,InterfaceA{
    @Override
    public void printSomething() {

        //Option 1 重新写一个 -> Provide our own implementation.
        System.out.println("I am inside Main class");

        //Option 2 选一个父亲 -> Use existing implementation from interfaceA or interfaceB or both.
        InterfaceB.super.printSomething(); //like "static"
        InterfaceA.super.printSomething();
    }

    @Override
    public double calculatePrice(Integer sku) {
        return 100;
    }

    public static void main(String[] args) {
        //new obj of InterfaceTest
        InterfaceTest interfaceTest = new InterfaceTest();
        //call method on interfaceTest obj
        interfaceTest.printSomething();
//        InterfaceTest.printSomething();
    }
}
