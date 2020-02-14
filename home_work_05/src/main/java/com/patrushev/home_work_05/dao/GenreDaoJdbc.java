package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public void insert(Genre genre) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        jdbc.update("insert into genres (id, name) values (:id, :name)", params);
    }

    @Override
    public Genre getById(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select * from genres where id = :id", params, new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public void update(Genre genre) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("id", genre.getId());
        params.put("name", genre.getName());
        jdbc.update("update genre set name = :name where id = :id", params);
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbc.update("delete from genre where id = :id", params);
    }

    @Override
    public int count() {
        // noinspection ConstantConditions
        return jdbc.queryForObject("select count(*) from genre", new HashMap<>(), Integer.class);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }
}
