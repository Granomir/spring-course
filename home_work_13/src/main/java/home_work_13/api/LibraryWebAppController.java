package home_work_13.api;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryWebAppController {

    @GetMapping("/library")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping("/library/add-book")
    @Secured({"ROLE_ADMIN"})
    public String addBookPage() {
        return "addBookPage";
    }

    @GetMapping("/library/edit-book/{bookId}")
    @Secured({"ROLE_ADMIN"})
    public String editBookPage() {
        return "editBookPage";
    }

}
