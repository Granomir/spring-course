package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Book;

public interface BookDao {
    void insert(Book book);
    Book getById(int id);
    void update(Book book);
    void deleteById(int id);
    int count();
}
