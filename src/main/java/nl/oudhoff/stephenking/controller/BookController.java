package nl.oudhoff.stephenking.controller;


import jakarta.validation.Valid;
import nl.oudhoff.stephenking.dto.input.BookInputDto;
import nl.oudhoff.stephenking.dto.input.ReviewInputDto;
import nl.oudhoff.stephenking.dto.output.BookOutputDto;
import nl.oudhoff.stephenking.exception.ResourceNotFoundException;
import nl.oudhoff.stephenking.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> createBooks(@Valid @RequestBody BookInputDto bookInputDto) {
            BookOutputDto createBook = bookService.createBook(bookInputDto);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + bookInputDto.getId()).toUriString());
        return ResponseEntity.ok().body(createBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookOutputDto> updateBook(@PathVariable long id, @RequestBody BookInputDto bookInputDto) {
        return ResponseEntity.ok(bookService.updateBook(id, bookInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOutputDto> getBookById(@PathVariable long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookOutputDto> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping
    public ResponseEntity<List<BookOutputDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable long id) throws ResourceNotFoundException {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Boek met id " + id + " is verwijderd!");
    }
}
