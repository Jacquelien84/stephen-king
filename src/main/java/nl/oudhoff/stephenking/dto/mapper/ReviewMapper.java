package nl.oudhoff.stephenking.dto.mapper;

import nl.oudhoff.stephenking.dto.input.ReviewInputDto;
import nl.oudhoff.stephenking.dto.output.ReviewOutputDto;
import nl.oudhoff.stephenking.model.Review;
import nl.oudhoff.stephenking.repository.BookRepository;


public class ReviewMapper {

    private static BookRepository bookRepo;

    public ReviewMapper(BookRepository bookRepo) {
        ReviewMapper.bookRepo = bookRepo;
    }

    public static Review fromInputDtoToModel (ReviewInputDto inputDto) {
        Review model = new Review();
        model.setUserId(inputDto.getUserId());
        model.setName(inputDto.getName());
        model.setReviewDate(inputDto.getReviewDate());
        model.setReviewText(inputDto.getReviewText());
        model.setAmountOfLikes(inputDto.getAmountOfLikes());
        return model;
    }

    public static ReviewOutputDto fromModelToOutputDto (Review model) {
        ReviewOutputDto outputDto = new ReviewOutputDto();
        outputDto.setUserId(model.getUserId());
        outputDto.setName(model.getName());
        outputDto.setReviewDate(model.getReviewDate());
        outputDto.setReviewText(model.getReviewText());
        outputDto.setAmountOfLikes(model.getAmountOfLikes());
        return outputDto;
    }
}
