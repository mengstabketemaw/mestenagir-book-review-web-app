package io.javabrains.betterreads.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.betterreads.book.Book;
import io.javabrains.betterreads.book.BookRepository;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

        List<Book> books;
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            books = Files.lines(new ClassPathResource("books").getFile().toPath())
                    .filter(line->line.contains(query.toLowerCase(Locale.ROOT)))
                    .map(line->{
                        try {
                            Book book = objectMapper.readValue(line,Book.class);
                            return book;
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("searchResults", books);
        return "search";
    }
    
}
