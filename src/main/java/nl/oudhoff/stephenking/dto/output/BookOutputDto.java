package nl.oudhoff.stephenking.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookOutputDto {
    public Long id;
    public String title;
    public String author;
    public String originalTitle;
    public Long released;
    public String movieAdaptation;
    public String description;
}
