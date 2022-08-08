package com.chuwa.exercise.oa.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
 *  gson.fromJson(string, target.class);
 */
public class FoodOutletJackson {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        List<String> seattle = FoodOutletJackson.getRelevantFoodOutlets11("Seattle", 140);
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
        FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
        String s = objectMapper.writeValueAsString(foodOutlet);
        System.out.println(s);

        int total_pages = foodOutlet.getTotal_pages();

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

    private static List<String> processData(String resBody, int maxCost) throws JsonProcessingException {

        FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);

        List<Data> datas = foodOutlet.getData();

        List<String> collect = datas.stream().filter(data -> data.getEstimated_cost() <= maxCost).map(data -> data.getName()).collect(Collectors.toList());

        return collect;
    }

    static class FoodOutlet {
        @JsonProperty("page")
        int page;

        @JsonProperty("per_page")
        int per_page;

        @JsonProperty("total")
        int total;

        @JsonProperty("total_pages")
        int total_pages;

        @JsonProperty("data")
        List<Data> data;

        public int getTotal_pages() {
            return total_pages;
        }

        public List<Data> getData() {
            return data;
        }
    }

    static class Data {
        @JsonProperty("city")
        String city;

        @JsonProperty("name")
        String name;

        @JsonProperty("estimated_cost")
        int estimated_cost;

        @JsonProperty("user_rating")
        UserRating user_rating;

        @JsonProperty("id")
        int id;

        public String getName() {
            return name;
        }

        public int getEstimated_cost() {
            return estimated_cost;
        }
    }

    static class UserRating {
        @JsonProperty("average_rating")
        float average_rating;

        @JsonProperty("votes")
        int votes;
    }


}
