package com.patrushev.home_work_06.dao;


import com.patrushev.home_work_06.model.Genre;

import java.util.List;

public interface GenreDao {
    long insert(Genre genre);
    Genre getById(long id);
    void update(Genre genre);
    void deleteById(long id);
    long count();
    List<Genre> getAll();
}
