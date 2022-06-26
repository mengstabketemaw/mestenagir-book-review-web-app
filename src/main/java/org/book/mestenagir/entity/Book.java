package org.book.mestenagir.entity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String title;

    String description;

    LocalDate publishedDate;

    String category;

    String address;
}
