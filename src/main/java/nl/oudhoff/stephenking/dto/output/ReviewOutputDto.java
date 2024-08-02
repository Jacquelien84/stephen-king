package nl.oudhoff.stephenking.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReviewOutputDto {
    private List<BookOutputDto> booksDtoList = new ArrayList<>();

    private long userId;
    private String name;
    private LocalDate reviewDate;
    private String reviewText;
    private long amountOfLikes;
}
