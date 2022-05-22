package org.book.mestenagir.entity;

import jdk.jfr.Enabled;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Enabled
@Data
public class Committee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToOne
    UserBook reviewedBook;

    @ManyToMany
    List<User> committeeList = new ArrayList<>();

    @ManyToMany
    List<Evaluation> evaluationList = new ArrayList<>();

}
