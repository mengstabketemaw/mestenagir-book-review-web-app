package org.book.mestenagir.controller;
import java.util.List;
import java.util.logging.Logger;

import org.book.mestenagir.entity.Book;
import org.book.mestenagir.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private final Logger logger = Logger.getLogger(SearchController.class.getName());

    private final BookRepository bookRepository;

    public SearchController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "/search")
    public String getSearchResults(@RequestParam String query, Model model) {
        System.out.println(query);
        List<Book> books=bookRepository.findByTitleContainingOrCategoryContainingAllIgnoreCase(query,query);
        if(books.size()==0)
            return "book-not-found";
        books.forEach(book-> book.setTitle(book.getTitle().replace(".pdf",".png")));
        model.addAttribute("searchResults", books);
        return "search";
    }

}
