package com.chuwa.tutorial.t06_java8.features.stream_api;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author b1go
 * @date 4/10/23 12:47 AM
 */
public class ProductClient {
    static List<Product> productList = Arrays.asList(
            new Product(1, "Product 1", "Electronics", 99.99, 20),
            new Product(2, "Product 2", "Electronics", 199.99, 15),
            new Product(3, "Product 3", "Electronics", 299.99, 5),
            new Product(4, "Product 4", "Clothing", 49.99, 30),
            new Product(5, "Product 5", "Clothing", 29.99, 25),
            new Product(6, "Product 6", "Clothing", 59.99, 10),
            new Product(7, "Product 7", "Kitchen", 89.99, 8),
            new Product(8, "Product 8", "Kitchen", 120.99, 2),
            new Product(9, "Product 9", "Kitchen", 60.99, 15)
    );

    @Test
    public void toList() {
        // Create a new list containing only the products with a stock greater than 10.
        List<Product> filteredProducts = productList.stream()
                .filter(product -> product.getStock() > 10)
                .collect(Collectors.toList());
    }

    @Test
    public void toSet() {
        // Create a new set containing the unique categories from the list of products.
        Set<String> uniqueCategories = productList.stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
    }

    @Test
    public void joining() {
        // Concatenate all product names into a single string, separated by commas.
        String allProductNames = productList.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));

        // Concatenate all product names into a single string, separated by a newline character.
        String allProductNamesNewLine = productList.stream()
                .map(Product::getName)
                .collect(Collectors.joining("\n"));
    }

    @Test
    public void counting() {
        // Count the number of products in each category.
        Map<String, Long> categoryCounts = productList.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    @Test
    public void summingInt() {
        // Calculate the total stock of all products.
        int totalStock = productList.stream()
                .collect(Collectors.summingInt(Product::getStock));
    }

    @Test
    public void groupingBy() {
        // Group the products by category and calculate the total stock for each category.
        Map<String, Integer> categoryStock = productList.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getStock)));

        // Group the products by category and calculate the average price for each category.
        Map<String, Double> categoryAveragePrice = productList.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));
    }

    @Test
    public void maxBy() {
        // 给定一个Product列表，使用Stream API的maxBy()方法找出价格最高的产品。
        Optional<Product> maxPriceProduct = productList.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Product::getPrice)));
    }

    @Test
    public void partitioningBy() {
        // 给定一个Product列表，使用Stream API的partitioningBy()方法按产品价格是否高于100分组。
        Map<Boolean, List<Product>> partitionedProducts = productList.stream()
                .collect(Collectors.partitioningBy(p -> p.getPrice() > 100));
    }

    @Test
    public void mapping() {
        Set<String> upperCaseNames = productList.stream()
                .map(Product::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }

    @Test
    public void match() {
        boolean isAnyProductExpensive = productList.stream()
                .anyMatch(product -> product.getPrice() > 250);
        System.out.println("Is there any expensive product? " + isAnyProductExpensive);

        // allMatch example
        boolean areAllProductsInStock = productList.stream()
                .allMatch(product -> product.getStock() > 0);
        System.out.println("Are all products in stock? " + areAllProductsInStock);

        // noneMatch example
        boolean areNoProductsFree = productList.stream()
                .noneMatch(product -> product.getPrice() == 0);
        System.out.println("Are there no free products? " + areNoProductsFree);
    }

    @Test
    public void findFirst() {
        // findFirst example
        Optional<Product> firstExpensiveProduct = productList.stream()
                .filter(product -> product.getPrice() > 100)
                .findFirst();
        firstExpensiveProduct.ifPresent(product -> System.out.println("First expensive product: " + product.getName()));
    }

    @Test
    public void findAny() {

        // findAny example
        Optional<Product> anyLowStockProduct = productList.stream()
                .filter(product -> product.getStock() < 10)
                .findAny();
        anyLowStockProduct.ifPresent(product -> System.out.println("Any low stock product: " + product.getName()));
    }
}
