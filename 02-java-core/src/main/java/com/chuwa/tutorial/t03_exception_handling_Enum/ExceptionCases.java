package com.chuwa.tutorial.t03_exception_handling_Enum;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/12/22 6:00 PM
 */
public class ExceptionCases {

    @Test
    public void testMethod1() {
        System.out.println(ExceptionCases.method1());
    }

    public static int method1() {
        int value = 1;
        try {
            throw new ArrayIndexOutOfBoundsException();
        } catch (ArrayIndexOutOfBoundsException e) {
            value = 2;
            return value;
        } finally {
            value += 2;
            return value;
        }
    }

    /**
     * unreachable throw when you have two throw
     */
    @Test
    public void testTwoThrows() {
        try {
            throw new NullPointerException(); // exception 1
//            throw new ArithmeticException();  // exception 2
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    /**
     * Can multiple catch clauses with any exception be in any order?
     */
    @Test
    public void testExceptionAnyOrder() {
        try {
            // exception thrown here
            throw new NumberFormatException();
        } catch (Exception e) {
            // exception caught here
            System.out.print("A");
        }
//        catch(NumberFormatException e){
//            System.out.print("B");
//            // Unreachable statement here
//        }catch(ArithmeticException e){
//            System.out.print("C");
//            // Unreachable statement here
//        }
    }

    /**
     * correct order
     * from small range to large range
     */
    @Test
    public void testExceptionRightOrder() {
        try {
            // exception thrown here
            throw new NumberFormatException();
        } catch (NumberFormatException e) { //sub-class
            // exception caught here
            System.out.print("A");
        } catch (ArithmeticException e) {// sub-class
            System.out.print("B");
            // Unreachable statement here
        } catch (Exception e) { //super-class
            System.out.print("C");
            // Unreachable statement here
        }
    }

    @Test
    public void testMultipleExceptions() {
        int a = 1;
        int b = 0;
        String s = null;

        try {
            // exception thrown here
//            System.out.print(a/b);
            s.toUpperCase();
        } catch (ArithmeticException | NullPointerException e) {
            // exception caught here
            System.out.print("A");
        }
    }

    public static int findNumber(int value) {
        Integer[] arr = null;

        try {
            System.out.println(arr[0]);
        } catch (NullPointerException e) {
            // exception caught here
            return value;
        } finally { // this is an optional block
            // will always be executed
            System.out.println("Finally executed");
        }
        return value;
    }

    /**
     * 即便返回return，也依然要先执行finally。
     */
    @Test
    public void testFindNumber() {
        int number = ExceptionCases.findNumber(5);
        System.out.println("value returned:" + number);
    }

    public static int[] method2() {
        int value = 10;
        int[] arr = {1};
        try {
            System.out.println(arr[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught value is " + arr[0]);
            return arr; // value is being returned from inside catch
        } finally {
            arr[0] = 2;
            System.out.println("Finally value is " + arr[0]);
        }
        return arr; //returning a referential type variable
    }

    /**
     * Returning referential values
     */
    @Test
    public void testMethods() {
        int[] ints = ExceptionCases.method2();
        System.out.println(ints[0]);
    }

}
