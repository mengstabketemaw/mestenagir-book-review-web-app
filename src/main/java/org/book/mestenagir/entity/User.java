package org.book.mestenagir.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String username;

    String password;

    String role;

    @OneToMany
    List<UserBook> myBooks = new ArrayList<>();

}
