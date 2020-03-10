package com.patrushev.home_work_06.dao;


import com.patrushev.home_work_06.model.Author;

import java.util.List;

public interface AuthorDao {
    long insert(Author author);
    Author getById(long id);
    void update(Author author);
    void deleteById(long id);
    long count();
    List<Author> getAll();
}
