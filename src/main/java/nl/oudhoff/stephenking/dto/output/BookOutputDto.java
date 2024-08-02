package nl.oudhoff.stephenking.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookOutputDto {
    private List<ReviewOutputDto> reviewDtoList = new ArrayList<>();

    private Long id;
    private String title;
    private String author;
    private String originalTitle;
    private Long released;
    private String movieAdaptation;
    private String description;
}
