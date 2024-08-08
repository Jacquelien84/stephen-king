package nl.oudhoff.stephenking.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewOutputDto {

    private Long id;
    private String name;
    private LocalDate reviewDate;
    private String reviewText;
    // Deze is nodig voor de koppeling van review en boek
    private Long bookId;
}
