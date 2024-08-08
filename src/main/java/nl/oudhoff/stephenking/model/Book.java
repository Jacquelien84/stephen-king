package nl.oudhoff.stephenking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String originalTitle;
    @Column(nullable = false)
    private Long released;
    @Column(nullable = false)
    private String movieAdaptation;
    @Column(length = 5000, nullable = false)
    private String description;

    //De koppelingscode tussen boek en review
    @OneToMany(mappedBy = "book")
    private List<Review> listOfReviews = new ArrayList<>();
    }
