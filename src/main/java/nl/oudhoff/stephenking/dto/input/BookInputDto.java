package nl.oudhoff.stephenking.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInputDto {

    public long id;
    public String title;
    public String author;
    public String originalTitle;
    public long released;
    public String movieAdaptation;
    public String description;

}
