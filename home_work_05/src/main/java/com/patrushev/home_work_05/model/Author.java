package com.patrushev.home_work_05.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Author {

    public Author(String name) {
        this.name = name;
    }

    private int id;
    private String name;
}
