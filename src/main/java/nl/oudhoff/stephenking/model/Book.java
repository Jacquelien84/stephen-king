package nl.oudhoff.stephenking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
}
