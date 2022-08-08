package com.chuwa.exercise.oa.api;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
 *  Java 8 版本
 */
public class FoodOutletsJava8 {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> seattle = FoodOutletsJava8.getRelevantFoodOutlets11("Seattle", 140);
        System.out.println(seattle);
    }

    public static List<String> getRelevantFoodOutlets11(String city, int maxCost) throws IOException {
        List<String> res = new ArrayList<>();

        String BASE_URL = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city;

        String URL_Addr = BASE_URL + "&page=1";

        // 处理page 1
        String resBody = callURL(URL_Addr);
        List<String> strings = processData(resBody, maxCost);
        res.addAll(strings);

        // get total_pages
        JsonObject jsonBody = new JsonParser().parse(resBody).getAsJsonObject();

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

    private static String callURL(String URL_Addr) throws IOException {
        URL url = new URL(URL_Addr);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new
                InputStreamReader(con.getInputStream()));
        String line = br.readLine();

        // only return response body
        return line;
    }

    private static List<String> processData(String resBody, int maxCost) {
        List<String> res = new ArrayList<>();

        JsonObject jsonBody = new JsonParser().parse(resBody).getAsJsonObject();

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
