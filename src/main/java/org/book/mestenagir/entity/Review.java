package org.book.mestenagir.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    int pageNumber;

    int lineNumber;

    @Lob
    String review;
}
