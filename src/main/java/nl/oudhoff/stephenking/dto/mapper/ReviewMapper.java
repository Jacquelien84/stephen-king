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

    public static Review fromInputDtoToModel(ReviewInputDto inputDto) {
        Review r = new Review();
        r.setName(inputDto.getName());
        r.setReviewDate(inputDto.getReviewDate());
        r.setReviewText(inputDto.getReviewText());
        return r;
    }

    public static ReviewOutputDto fromModelToOutputDto(Review review) {
        ReviewOutputDto reviewOutputDto = new ReviewOutputDto();
        reviewOutputDto.setId(review.getId());
        reviewOutputDto.setName(review.getName());
        reviewOutputDto.setReviewDate(review.getReviewDate());
        reviewOutputDto.setReviewText(review.getReviewText());
        if (review.getBook() != null) {
            reviewOutputDto.setBookId(review.getBook().getId());
        }
            return reviewOutputDto;
        }
    }

