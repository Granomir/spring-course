package com.patrushev.home_work_05.model;

import lombok.Data;

@Data
public class Book {
    private final int id;
    private final String title;
    private final Author author;
    private final Genre genre;
}
