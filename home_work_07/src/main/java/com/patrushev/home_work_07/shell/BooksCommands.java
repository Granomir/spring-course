package com.patrushev.home_work_07.shell;

import com.google.gson.Gson;
import com.patrushev.home_work_07.model.Book;
import com.patrushev.home_work_07.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BooksCommands {
    private final BookRepository repository;

    @ShellMethod(value = "Insert book.", key = "insBook")
    public Book insert(@ShellOption(arity = 1) Book book) {
        return repository.save(book);
    }

    @ShellMethod(value = "Get book by id.", key = "getBook")
    public Book getById(@ShellOption(arity = 1) long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ShellMethod(value = "Update book.", key = "updBook")
    public void update(@ShellOption(arity = 1) Book book) {
        repository.save(book);
    }

    @ShellMethod(value = "Delete book.", key = "delBook")
    public void deleteById(@ShellOption(arity = 1) long id) {
        repository.deleteById(id);
    }

    @ShellMethod(value = "Count books.", key = "countBooks")
    public long count() {
        return repository.count();
    }

    @ShellMethod(value = "Get all books.", key = "getBooks")
    public List<Book> getAll() {
        return repository.findAll();
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

