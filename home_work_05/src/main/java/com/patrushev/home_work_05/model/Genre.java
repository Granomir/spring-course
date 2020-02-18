package com.patrushev.home_work_05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genre {
    private int id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
