package com.chuwa.tutorial.t03_exception_handling_Enum;

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

        Client client = new Client();

        System.out.println("call uber on Tuesday");
        client.callUber(2);

        System.out.println("call uber on Monday");
        try{
            client.callUber(1);
        }catch (ApiException e){
            System.out.println("failed to call Uber...because too busy");
        }finally{
            System.out.println("failed to call Uber...walk back home");
        }

    }

    public static void callApi() {
        throw new ApiException(ResultCode.UNAUTHORIZED);
    }

    public void callUber(int day) throws ApiException { //It might "throws". Not Must.
        if(day % 2 == 0){ //99%
            System.out.println("Successfully get Uber order");
        }else{ //1% .. 0.01%
            throw new ApiException(ResultCode.FAILED); //1-999
        }
    }
}
