package org.book.mestenagir.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToMany
    List<Review> review;

    @Lob
    String summary;

    String decision;

}
