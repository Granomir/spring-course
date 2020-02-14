package com.patrushev.home_work_05.dao;

import com.patrushev.home_work_05.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository
@RequiredArgsConstructor
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", author.getName());
        jdbc.update("insert into authors (name) values (:name)", params, keyHolder);
        return (int) keyHolder.getKey();
    }

    @Override
    public Author getById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbc.queryForObject("select * from authors where id = :id", params, new AuthorMapper());
    }

    @Override
    public void update(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", author.getId());
        params.addValue("name", author.getName());
        jdbc.update("update authors set name = :name where id = :id", params);
    }

    @Override
    public void deleteById(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbc.update("delete from authors where id = :id", params);
    }

    @Override
    public int count() {
        // noinspection ConstantConditions
        return jdbc.queryForObject("select count(*) from authors", new HashMap<>(), Integer.class);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Author(id, name);
        }
    }
}
