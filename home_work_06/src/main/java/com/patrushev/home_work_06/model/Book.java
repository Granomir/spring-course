package com.patrushev.home_work_06.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @Column(name = "author")
    private Author author;
    //TODO нужны ли тут join'ы???
    @ManyToOne
    @Column(name = "genre")
    private Genre genre;

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
