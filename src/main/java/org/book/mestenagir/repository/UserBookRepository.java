package org.book.mestenagir.repository;

import org.book.mestenagir.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookRepository extends JpaRepository<UserBook,Long> {
}
