package com.chuwa.exercise.oa.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

class Capital {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        String canada = Capital.getCapital("Canada");
        System.out.println(canada);
    }

    public static String getCapital(String country)
    {
        try{
            Gson gson = new Gson();
            String co = country.replace(" ", "%20");
            URL url = new URL("https://jsonmock.hackerrank.com/api/countries?name=" + co);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = br.readLine();
            System.out.println("line : " + line);
            Country c = gson.fromJson(line, Country.class);
            Data d = c.data.get(0);
            String capital = d.capital;
            return capital;
        }catch(Exception e){
            System.out.println(e);
        }
        return "-1";
    }

    static class Country{
        int page, per_page, total, total_pages;
        List<Data> data;
    }
    static class Data{
        String name, capital;
        List<String> callingCodes;
    }

}

