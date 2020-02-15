package com.patrushev.home_work_05.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private int id;
    private String title;
    private Author author;
    private Genre genre;
}
