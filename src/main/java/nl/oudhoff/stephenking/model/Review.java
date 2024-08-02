package nl.oudhoff.stephenking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Review {
    @Id
    private long userId;
    private String name;
    private LocalDate reviewDate;
    private String reviewText;
    private long amountOfLikes;

    @ManyToMany
    private Set<Book> books = new HashSet<>();
}
