package org.book.mestenagir.controller;
import java.util.Map;

import org.book.mestenagir.entity.UserBook;
import org.book.mestenagir.service.BookStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());
    private final BookStoreService bookStoreService;

    public HomeController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal == null || principal.getAttribute("login") == null) {
            return "index";
        }
        //for now, this only work for GitHub authentication.
        Map<String, Object> userDetails = principal.getAttributes();
        String username = (String) userDetails.get("login");
        logger.info("Authenticated user has been Logged in "+username);

        //return all the book of the user.
        var myBooks = bookStoreService.getMyBook(username);
        model.addAttribute("isEmpty",(myBooks.size()==0));
        model.addAttribute("books",myBooks);
        return "home";
    }

    @PostMapping("/addbook")
    public ResponseEntity<Void> addBook(@RequestParam String title, @RequestParam String description, @RequestPart MultipartFile coverImage, @RequestPart MultipartFile bookFile, @RequestParam boolean isPublished){
        bookStoreService.saveMyBook(title,description,coverImage,bookFile,isPublished);
        return ResponseEntity.ok().build();
    }
}
