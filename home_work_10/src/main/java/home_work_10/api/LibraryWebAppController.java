package home_work_10.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryWebAppController {

    @Autowired
    public LibraryWebAppController() {
    }

    @GetMapping("/library")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/library/add-book")
    public String addBookPage() {
        return "addBookPage";
    }

    @GetMapping("/library/edit-book")
    public String editBookPage() {
        return "editBookPage";
    }

}
