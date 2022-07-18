package com.chuwa.tutorial.exception_handling_Enum;

/**
 * @author b1go
 * @date 5/12/22 11:30 PM
 */
public class Client {

    public static void main(String[] args) {
        try {
            Client.callApi();
        } catch (ApiException e) {
            System.out.println(e);
        }
    }

    public static void callApi() {
        throw new ApiException(ResultCode.UNAUTHORIZED);
    }
}
