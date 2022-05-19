package io.javabrains.betterreads.book;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CassandraRepository<Book, String> {
    @AllowFiltering
    List<Book> findByName(String name);
}
