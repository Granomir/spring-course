package com.patrushev.home_work_06;

import com.google.gson.Gson;
import com.patrushev.home_work_06.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeWork06ApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        serializeAuthor();
    }

    @Test
    static void serializeAuthor() {
        Comment comment = new Comment(0, "Roman", "Cool story!", 1);
        Gson gson = new Gson();
        System.out.println(gson.toJson(comment));
    }

}
