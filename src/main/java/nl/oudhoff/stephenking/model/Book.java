package nl.oudhoff.stephenking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    private Set<Review> reviews = new HashSet<>();
}
