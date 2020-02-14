package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Book;

import java.util.List;

public interface BookDao {
    int insert(Book book);
    Book getById(int id);
    void update(Book book);
    void deleteById(int id);
    int count();
    List<Book> getAll();
}
