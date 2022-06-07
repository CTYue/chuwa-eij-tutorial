package com.chuwa.junitdemo;

/**
 * @author b1go
 * @date 5/22/22 5:01 PM
 */
public class DemoUtils {

    private String academy = "Chuwa Academy";
    private String academyDuplicate = academy;

    public int add(int a, int b) {
        return a + b;
    }

    public int addWrong(int a, int b) {
        return a + b + b;
    }

    public Object checkNull(Object obj) {

        if (obj != null) {
            return obj;
        }
        return null;

    }

    public String getAcademy() {
        return academy;
    }

    public String getAcademyDuplicate() {
        return academyDuplicate;
    }

    public Boolean isGreater(int n1, int n2) {
        if (n1 > n2) {
            return true;
        }
        return false;
    }

    public String throwException(int a) throws Exception {
        if (a < 0) {
            throw new Exception("Value should be greater than or equal to 0");
        }
        return "Value is greater than or equal to 0";
    }
}
