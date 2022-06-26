package org.book.mestenagir.service;

import org.book.mestenagir.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BookStoreService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookStoreService(BookRepository bookRepository,
                            UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<UserBook> getMyBook(String username){
        User user = userRepository.findByUsername(username)
                .orElseGet(()->{
                    User newUser = new User();
                    newUser.setUsername(username);
                    userRepository.save(newUser);
                    return newUser;
                });
        return user.getMyBooks();
    }

    public void saveMyBook(String title, String description, MultipartFile coverImage, MultipartFile bookFile, boolean isPublished) {

    }
}
