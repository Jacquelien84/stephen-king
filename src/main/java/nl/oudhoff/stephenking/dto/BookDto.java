package nl.oudhoff.stephenking.dto;

import jakarta.persistence.Column;

public class BookDto {
    public Long id;
    public String title;
    public String author;
    public String originalTitle;
    public Long released;
    public String movieAdaptation;
    @Column(length = 5000)
    public String description;
}
