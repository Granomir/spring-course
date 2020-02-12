package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;

public interface AuthorDao {
    void insert(Author author);
    Author getById(int id);
    void update(Author author);
    void delete(int id);
    int count();
}
