package com.chuwa.exercise.oa.api;


import com.google.gson.*;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author b1go
 * @date 8/7/22 12:48 AM
 *
 * Requirements:
 *  1, Java 11 或 以上 -> call api
 *  2, Gson: import com.google.gson.*;
 *      not selected: JackSon: import com.fasterxml.jackson.databind.ObjectMapper;  -> objectMapper.readValue(s, FoodOutlets.class) food -> food.getEstimatedCost() <= maxCost -> food.getName;
 *  3, if "estimated_cost" < maxCost  -> "name"
 *
 *  Java 11
 */
public class FoodOutletsJava11 {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> seattle = FoodOutletsJava11.getRelevantFoodOutlets11("Seattle", 140);
        System.out.println(seattle);
    }

    public static List<String> getRelevantFoodOutlets11(String city, int maxCost) throws IOException, URISyntaxException, InterruptedException {
        List<String> res = new ArrayList<>();

        String BASE_URL = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city;

        String URL_Addr = BASE_URL + "&page=1";

        // 处理page 1
        String resBody = callURL(URL_Addr);
        List<String> strings = processData(resBody, maxCost);
        res.addAll(strings);

        // get total_pages
        JsonObject jsonBody = (JsonObject) JsonParser.parseString(resBody);
        int total_pages = Integer.parseInt(jsonBody.get("total_pages").getAsString());

        // 处理剩余page: 2 - last page
        for (int i = 2; i <= total_pages; i++) {
            URL_Addr = BASE_URL + "&page=" + i;
            resBody = callURL(URL_Addr);
            strings = processData(resBody, maxCost);
            res.addAll(strings);
        }

        return res;
    }

    private static String callURL(String URL_Addr) throws IOException, InterruptedException, URISyntaxException {
        // define httpClient;
        HttpClient httpClient = HttpClient.newBuilder().build();
        // define a HttpRequest
        HttpRequest request = HttpRequest.newBuilder(new URI(URL_Addr))
                // 设置Header:
                .header("User-Agent", "Java HttpClient").header("Accept", "*/*")
                // 设置超时:
                .timeout(Duration.ofSeconds(5))
                // 设置版本:
                .version(HttpClient.Version.HTTP_2).build();

        // call api
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // HTTP允许重复的Header，因此一个Header可对应多个Value:
        Map<String, List<String>> headers = response.headers().map();
        for (String header : headers.keySet()) {
            System.out.println(header + ": " + headers.get(header).get(0));
        }

        // check status code
        if (response.statusCode() != 200) {
            throw new RuntimeException("bad response");
        }

        // only return response body
        return response.body();
    }

    private static List<String> processData(String resBody, int maxCost) {
        List<String> res = new ArrayList<>();

        JsonObject jsonBody = (JsonObject) JsonParser.parseString(resBody);

        JsonArray jsonArray = jsonBody.get("data").getAsJsonArray();
        jsonArray.forEach(d -> {
            int estimated_cost = d.getAsJsonObject().get("estimated_cost").getAsInt();
            if (estimated_cost <= maxCost) {
                res.add(d.getAsJsonObject().get("name").getAsString());
            }
        });

        return res;
    }

}
