package org.book.mestenagir.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.book.mestenagir.entity.Book;
import org.book.mestenagir.entity.BookSources;
import org.book.mestenagir.repository.BookRepository;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

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

//    @PostConstruct
//    public void initDataBase(){
//        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
//        try (Stream<String> books = Files.lines(new ClassPathResource("books").getFile().toPath())) {
//
//            books.limit(10)
//                    .forEach(line->{
//                        Map<String,Object> map = jacksonJsonParser.parseMap(line);
//                        Book book = new Book();
//                        book.setTitle((String)map.get("name"));
//                        book.setCategory((String)map.get("category"));
//                        book.setSource(BookSources.EOTC);
//                        book.setAddress((String)map.get("address"));
//                        System.out.println("saving book: "+book.getTitle());
//                        bookRepository.save(book);
//                    });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
