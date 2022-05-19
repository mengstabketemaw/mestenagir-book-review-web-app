package io.javabrains.betterreads.search;

import java.util.List;

import io.javabrains.betterreads.book.Book;
import io.javabrains.betterreads.book.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private final BookRepository bookRepository;

    public SearchController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "/search")
    public String getSearchResults(@RequestParam String query, Model model) {
        List<Book> books = bookRepository.findByName(query);
        model.addAttribute("searchResults", books);
        return "search";
    }
    
}
