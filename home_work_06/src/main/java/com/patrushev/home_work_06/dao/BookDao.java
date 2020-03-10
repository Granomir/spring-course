package com.patrushev.home_work_06.dao;


import com.patrushev.home_work_06.model.Book;

import java.util.List;

public interface BookDao {
    long insert(Book book);
    Book getById(long id);
    void update(Book book);
    void deleteById(long id);
    int count();
    List<Book> getAll();
}
