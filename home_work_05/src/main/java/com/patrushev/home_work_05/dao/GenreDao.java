package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Genre;

public interface GenreDao {
    void insert(Genre genre);
    Genre getById(int id);
    void update(Genre genre);
    void deleteById(int id);
    int count();
}
