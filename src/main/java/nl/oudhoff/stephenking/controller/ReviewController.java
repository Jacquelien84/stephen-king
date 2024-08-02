package nl.oudhoff.stephenking.controller;

import jakarta.validation.Valid;
import nl.oudhoff.stephenking.dto.input.ReviewInputDto;
import nl.oudhoff.stephenking.dto.output.ReviewOutputDto;
import nl.oudhoff.stephenking.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewInputDto reviewInputDto, BindingResult br) {
        try {
            if (br.hasFieldErrors()) {
                StringBuilder sb = new StringBuilder();
                for (FieldError fe : br.getFieldErrors()) {
                    sb.append(fe.getField());
                    sb.append(" : ");
                    sb.append(fe.getDefaultMessage());
                    sb.append("\n");
                }
                return ResponseEntity.badRequest().body(sb.toString());
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body("Creation failed");
        }
        ReviewOutputDto reviewOutputDto = reviewService.createReview(reviewInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + reviewOutputDto.getUserId()).toUriString());

        return ResponseEntity.created(uri).body(reviewOutputDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ReviewOutputDto> updateReview(@RequestBody ReviewInputDto reviewInputDto, @PathVariable String userId) {
        ReviewOutputDto updateReview = reviewService.updateReview(Long.parseLong(userId), reviewInputDto);
        return ResponseEntity.ok().body(updateReview);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ReviewOutputDto> getReviewByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(reviewService.getReviewByUserId(userId));
    }

    @GetMapping("/name")
    public ResponseEntity<ReviewOutputDto> getReviewByName(@RequestParam String name) {
        return ResponseEntity.ok(reviewService.getReviewByName(name));
    }

    @GetMapping
    public ResponseEntity<List<ReviewOutputDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteReviewByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(reviewService.deleteReviewByUserId(userId));
    }
}
