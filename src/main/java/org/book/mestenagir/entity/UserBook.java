package org.book.mestenagir.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String title;

    String description;

    boolean published;

    @Lob
    byte[] coverImage;

    @Lob
    byte[] bookFile;

}
