package org.book.mestenagir.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String username;

    @OneToMany
    List<UserBook> myBooks;

}
