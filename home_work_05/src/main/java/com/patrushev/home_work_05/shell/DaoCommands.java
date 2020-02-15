//package com.patrushev.home_work_05.shell;
//
//import com.patrushev.home_work_05.dao.BookDao;
//import com.patrushev.home_work_05.model.Book;
//import lombok.RequiredArgsConstructor;
//import org.springframework.shell.standard.ShellComponent;
//import org.springframework.shell.standard.ShellMethod;
//import org.springframework.shell.standard.ShellOption;
//
//import java.util.List;
//
//@ShellComponent
//@RequiredArgsConstructor
//public class DaoCommands {
//    private final BookDao dao;
//
//    @ShellMethod(value = "Insert book.", key = "insBook")
//    public int insert(@ShellOption(arity = 2) Book book) {
//        return 0;
//    }
//
//    @ShellMethod(value = "Get book by id.", key = "getBook")
//    public Book getById(@ShellOption(arity = 2) int id) {
//        return null;
//    }
//
//    @ShellMethod(value = "Update book.", key = "updBook")
//    public void update(@ShellOption(arity = 2) Book book) {
//
//    }
//
//    @ShellMethod(value = "Delete book.", key = "delBook")
//    public void deleteById(@ShellOption(arity = 2) int id) {
//
//    }
//
//    @ShellMethod(value = "Count books.", key = "countBooks")
//    public int count() {
//        return 0;
//    }
//
//    @ShellMethod(value = "Get all books.", key = "getBooks")
//    public List<Book> getAll() {
//        return null;
//    }
//
////    @Component
////    private static class CustomDomainConverter implements Converter<String, DomainObject> {
////
////        @Override
////        public DomainObject convert(String source) {
////            return new DomainObject(source);
////        }
////    }
//}
//
