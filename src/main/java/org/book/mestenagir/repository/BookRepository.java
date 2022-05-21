package org.book.mestenagir.repository;

import org.book.mestenagir.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleContainingOrCategoryContainingAllIgnoreCase(String title,String category);
}
