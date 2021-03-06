package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;

import java.util.List;

public interface AuthorDao {
    int insert(Author author);
    Author getById(int id);
    void update(Author author);
    void deleteById(int id);
    int count();
    List<Author> getAll();
}
