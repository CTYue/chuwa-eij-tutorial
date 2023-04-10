package com.chuwa.tutorial.t08_multithreading.c08_future;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * @author b1go
 * @date 4/9/23 11:51 PM
 */
public class ApiIntegration {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static CompletableFuture<String> getProductInformation() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.bestbuy.com/v1/products?format=json&apiKey=YOUR_API_KEY"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getReviews() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.yelp.com/v3/businesses/{business_id}/reviews"))
                .header("Authorization", "Bearer YOUR_API_KEY")
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    public static CompletableFuture<String> getInventory() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://world.openfoodfacts.org/api/v0/product/{barcode}.json"))
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }
}

