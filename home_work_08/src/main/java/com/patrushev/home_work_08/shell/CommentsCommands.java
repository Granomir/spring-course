package com.patrushev.home_work_08.shell;

import com.google.gson.Gson;
import com.patrushev.home_work_08.model.Comment;
import com.patrushev.home_work_08.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class CommentsCommands implements CommentsService {
    private final CommentsService service;

    @Override
    @ShellMethod(value = "Add comment.", key = "addComment")
    public void addComment(@ShellOption Comment comment) {
        service.addComment(comment);
    }

    @Override
    @ShellMethod(value = "Delete comment.", key = "delComment")
    public void deleteComment(@ShellOption long id) {
        service.deleteComment(id);
    }

    @Override
    @ShellMethod(value = "Edit comment.", key = "editComment")
    public void editComment(@ShellOption long id, @ShellOption String body) {
        service.editComment(id, body);
    }

    @Override
    @ShellMethod(value = "Get comments.", key = "getComments")
    public List<Comment> getComments(@ShellOption long bookId) {
        return service.getComments(bookId);
    }

    @Component
    private static class CustomDomainConverter implements Converter<String, Comment> {
        @Override
        public Comment convert(String source) {
            Gson gson = new Gson();
            return gson.fromJson(source, Comment.class);
        }
    }
}
