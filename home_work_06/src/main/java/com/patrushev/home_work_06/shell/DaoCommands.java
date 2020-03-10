package com.patrushev.home_work_06.shell;

import com.google.gson.Gson;
import com.patrushev.home_work_06.dao.BookDao;
import com.patrushev.home_work_06.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class DaoCommands {
    private final BookDao dao;

    @ShellMethod(value = "Insert book.", key = "insBook")
    public long insert(@ShellOption(arity = 1) Book book) {
        return dao.insert(book);
    }

    @ShellMethod(value = "Get book by id.", key = "getBook")
    public Book getById(@ShellOption(arity = 1) int id) {
        return dao.getById(id);
    }

    @ShellMethod(value = "Update book.", key = "updBook")
    public void update(@ShellOption(arity = 1) Book book) {
        dao.update(book);
    }

    @ShellMethod(value = "Delete book.", key = "delBook")
    public void deleteById(@ShellOption(arity = 1) int id) {
        dao.deleteById(id);
    }

    @ShellMethod(value = "Count books.", key = "countBooks")
    public long count() {
        return dao.count();
    }

    @ShellMethod(value = "Get all books.", key = "getBooks")
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Component
    private static class CustomDomainConverter implements Converter<String, Book> {
        @Override
        public Book convert(String source) {
            Gson gson = new Gson();
            return gson.fromJson(source, Book.class);
        }
    }
}

