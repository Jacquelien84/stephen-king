package nl.oudhoff.stephenking.service;

import nl.oudhoff.stephenking.dto.input.ReviewInputDto;
import nl.oudhoff.stephenking.dto.mapper.ReviewMapper;
import nl.oudhoff.stephenking.dto.output.ReviewOutputDto;
import nl.oudhoff.stephenking.exception.ResourceNotFoundException;
import nl.oudhoff.stephenking.model.Review;
import nl.oudhoff.stephenking.repository.BookRepository;
import nl.oudhoff.stephenking.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepo;
    private final BookRepository bookRepo;

    public ReviewService(BookRepository bookRepo, ReviewRepository reviewRepo) {
        this.reviewRepo = reviewRepo;
        this.bookRepo = bookRepo;
    }

    public ReviewOutputDto createReview(ReviewInputDto reviewInputDto) {
        Review model = ReviewMapper.fromInputDtoToModel(reviewInputDto);
        reviewRepo.save(model);
        return ReviewMapper.fromModelToOutputDto(model);
    }

    public ReviewOutputDto updateReview(long userId, ReviewInputDto reviewInputDto) {
        Optional<Review> review = reviewRepo.findByUserId(userId);
        if (review.isPresent()) {
            Review model = ReviewMapper.fromInputDtoToModel(reviewInputDto);
            reviewRepo.save(model);
            return ReviewMapper.fromModelToOutputDto(model);
        } else {
            throw new ResourceNotFoundException("Review met userId " + userId + " niet gevonden");
        }
    }

    public ReviewOutputDto getReviewByUserId(long userId) {
        Review review = reviewRepo.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Opmerking met id " + userId + " is niet gevonden"));
        return ReviewMapper.fromModelToOutputDto(review);
    }

    public ReviewOutputDto getReviewByName(String name) {
        Review review = reviewRepo.findByNameIgnoreCase(name).orElseThrow(() -> new ResourceNotFoundException("Opmerking van " + name + " is niet gevonden"));
        return ReviewMapper.fromModelToOutputDto(review);
    }

    public List<ReviewOutputDto> getAllReviews() {
        List<Review> allReviews = reviewRepo.findAll();
        List<ReviewOutputDto> allReviewOutputList = new ArrayList<>();
        for (Review review : allReviews) {
            allReviewOutputList.add(ReviewMapper.fromModelToOutputDto(review));
        }
        return allReviewOutputList;
    }

    public String deleteReviewByUserId(long userId) {
        Optional<Review> review = reviewRepo.findById(userId);
        if (review.isPresent()) {
            reviewRepo.delete(review.get());
            return "Review with id " + userId + " has been removed";
        } else {
            throw new ResourceNotFoundException("Review not found");
        }
    }
}
