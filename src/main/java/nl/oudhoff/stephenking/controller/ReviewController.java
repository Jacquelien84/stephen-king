package nl.oudhoff.stephenking.controller;

import jakarta.validation.Valid;
import nl.oudhoff.stephenking.dto.input.ReviewInputDto;
import nl.oudhoff.stephenking.dto.output.ReviewOutputDto;
import nl.oudhoff.stephenking.model.Review;
import nl.oudhoff.stephenking.service.BookService;
import nl.oudhoff.stephenking.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewInputDto reviewInputDto) {
        ReviewOutputDto createReview = reviewService.createReview(reviewInputDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + reviewInputDto.getId()).toUriString());
        return ResponseEntity.ok().body(createReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewOutputDto> updateReview(@RequestBody ReviewInputDto reviewInputDto, @PathVariable long id) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewOutputDto> getReviewById(@PathVariable long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    @GetMapping("/name")
    public ResponseEntity<ReviewOutputDto> getReviewByName(@RequestParam String name) {
        return ResponseEntity.ok(reviewService.getReviewByName(name));
    }

    @GetMapping
    public ResponseEntity<List<ReviewOutputDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable long id) {
        return ResponseEntity.ok(reviewService.deleteReviewById(id));
    }

    @PutMapping("/{reviewId}/books/{bookId}")
    public ResponseEntity<String> addReviewToBook(@PathVariable long reviewId, @PathVariable long bookId) {
        reviewService.addReviewToBook(reviewId, bookId);
        // builder pattern
        return ResponseEntity.ok().build();
    }
}