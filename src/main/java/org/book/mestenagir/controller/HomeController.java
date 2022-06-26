package org.book.mestenagir.controller;

import org.book.mestenagir.entity.Book;
import org.book.mestenagir.repository.BookRepository;
import org.book.mestenagir.service.BookStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());
    private final BookRepository bookRepository;
    public HomeController(BookStoreService bookStoreService, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        Page<Book> pageBooks = bookRepository.findAll(PageRequest.of(1, 10));
        List<Book> books = pageBooks.get()
                .collect(Collectors.toList());
        if(books.size()==0)
            return "book-not-found";
        books.forEach(book-> book.setTitle(book.getTitle().replace(".pdf",".png")));
        model.addAttribute("searchResults", books);
        return "search";
    }
}
