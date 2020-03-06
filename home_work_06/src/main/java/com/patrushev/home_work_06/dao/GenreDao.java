package com.patrushev.home_work_06.dao;


import com.patrushev.home_work_06.model.Genre;

import java.util.List;

public interface GenreDao {
    int insert(Genre genre);
    Genre getById(int id);
    void update(Genre genre);
    void deleteById(int id);
    int count();
    List<Genre> getAll();
}
