package com.patrushev.home_work_05;

import com.google.gson.Gson;
import com.patrushev.home_work_05.model.Author;
import com.patrushev.home_work_05.model.Book;
import com.patrushev.home_work_05.model.Genre;
import org.junit.jupiter.api.Test;

class HomeWork05ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void serializeBook() {
        Book book = new Book(0, "Война и мир", new Author(1, "Толстой"), new Genre(1, "Классика"));
        Gson gson = new Gson();
        System.out.println(gson.toJson(book));
        String json = "{\"title\":\"Война и мир\",\"author\":{\"id\":1,\"name\":\"Толстой\"},\"genre\":{\"id\":1,\"name\":\"Классика\"}}";
        System.out.println(gson.fromJson(json, Book.class));
    }

    @Test
    void serializeAuthor() {
        Author author = new Author(1, "Толстой");
        Gson gson = new Gson();
        System.out.println(gson.toJson(author));
    }

    @Test
    void serializeGenre() {
        Genre genre = new Genre(1, "Классика");
        Gson gson = new Gson();
        System.out.println(gson.toJson(genre));
    }

}
