package com.chuwa.exercise.client;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author b1go
 * @date 7/7/23 9:01 PM
 *
 * Input: List<Book> = [book1, book2, ... book6]
 * Output: List<List<Book>> : [[book1], [book4], [book2, book5], [book3, book6]]
 *
 * book1 -> categories -> [879, 345, 243]
 */
public class SolutionBooksCategory {
    public static void main(String[] args) {

        // init categories
        Category category243 = new Category(243L, 1, null);

        Category category345 = new Category(345L, 2, category243);
        Category category564 = new Category(564L, 2, category243);

        Category category879 = new Category(879L, 3, category345);
        Category category532 = new Category(532L, 3, category345);
        Category category645 = new Category(645L, 3, category564);
        Category category113 = new Category(113L, 3, category564);

        Category category434 = new Category(434L, 4, category645);
        Category category189 = new Category(189L, 4, category113);

        Category category437 = new Category(437L, 5, category434);

        Category category123 = new Category(123L, 6, category437);

        // init books
        Book book1 = new Book("book1", Arrays.asList(category879, category345, category243));
        Book book4 = new Book("book4", Arrays.asList(category532, category345, category243));
        Book book2 = new Book("book2", Arrays.asList(category123, category437, category434, category645, category564, category243));
        Book book5 = new Book("book5", Arrays.asList(category123, category437, category434, category645, category564, category243));
        Book book3 = new Book("book3", Arrays.asList(category189, category113, category564, category243));
        Book book6 = new Book("book6", Arrays.asList(category189, category113, category564, category243));

        List<Book> books = Arrays.asList(book1, book2, book3, book4, book5, book6);

        print(solver(books));

        print(solverWithGroup(books));
    }

    public static List<List<Book>> solver(List<Book> books) {
        Map<Category, List<Book>> categoryBookMap = new HashMap<>();
        for (Book book : books) {
            Category leafCategory = findLeafCategory(book);
            categoryBookMap.computeIfAbsent(leafCategory, k -> new ArrayList<>()).add(book);
        }

        return new ArrayList<>(categoryBookMap.values());
    }

    public static List<List<Book>> solverWithGroup(List<Book> books) {
        Map<Category, List<Book>> categoryBookMap = new HashMap<>();
        for (Book book : books) {
            Category leafCategory = findLeafCategory(book);
            categoryBookMap.computeIfAbsent(leafCategory, k -> new ArrayList<>()).add(book);
        }

        PriorityQueue<Map.Entry<Category, List<Book>>> queue = new PriorityQueue<>(
                (e1, e2) -> Integer.compare(e2.getKey().getLevel(), e1.getKey().getLevel()));

        queue.addAll(categoryBookMap.entrySet());

        while (queue.size() > 3) {
            Map.Entry<Category, List<Book>> entryToMerge = queue.poll();
            Map.Entry<Category, List<Book>> nextEntry = queue.peek();
            nextEntry.getValue().addAll(entryToMerge.getValue());
        }

        List<List<Book>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().getValue());
        }

        return result;
    }

    public static List<List<String>> convertToTitleLists(List<List<Book>> bookOfBooks) {
        return bookOfBooks.stream()
                .map(bookList -> bookList.stream()
                        .map(Book::getTitle)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static void print(List<List<Book>> books) {
        List<List<String>> toTitleLists = convertToTitleLists(books);
        System.out.println(toTitleLists.toString());
    }

    private static Category findLeafCategory(Book book) {
        return book.getCategories().get(0);
    }



}

class Category {
    public Long id;
    public String name;
    public Integer level;
    public Category parent;

    public Category(Long id, Integer level, Category parent) {
        this.id = id;
        this.level = level;
        this.parent = parent;
    }

    public Integer getLevel() {
        return level;
    }
}

class Book {
    public Long id;
    public String title;
    // categories[0] -> leaf category
    // categories[last] -> root category
    public List<Category> categories; // book1 -> categories -> [879, 345, 243]
    public String condition;

    public Book(String title, List<Category> categories) {
        this.title = title;
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getTitle() {
        return title;
    }
}