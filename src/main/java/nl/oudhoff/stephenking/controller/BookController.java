package nl.oudhoff.stephenking.controller;

import nl.oudhoff.stephenking.model.Book;
import nl.oudhoff.stephenking.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepos;
    public BookController(BookRepository repos) {
        this.bookRepos = repos;
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepos.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        this.bookRepos.save(book);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + book.getId()).toUriString());
        return ResponseEntity.created(uri).body(book);
    }
}
