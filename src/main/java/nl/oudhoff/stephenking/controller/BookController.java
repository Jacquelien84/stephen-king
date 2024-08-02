package nl.oudhoff.stephenking.controller;


import jakarta.validation.Valid;
import nl.oudhoff.stephenking.dto.input.BookInputDto;
import nl.oudhoff.stephenking.dto.output.BookOutputDto;
import nl.oudhoff.stephenking.exception.ResourceNotFoundException;
import nl.oudhoff.stephenking.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    // Kijken of ik een mapper kan maken voor het try stuk
    @PostMapping
    public ResponseEntity<?> createBooks(@Valid @RequestBody BookInputDto bookInputDto, BindingResult br) {
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
        BookOutputDto bookOutputDto = bookService.createBook(bookInputDto);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + bookOutputDto.getId()).toUriString());
        return ResponseEntity.created(uri).body(bookOutputDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookOutputDto> updateBook(@PathVariable long id, @RequestBody BookInputDto bookInputDto) {
        BookOutputDto updatedBook = bookService.updateBook(id, bookInputDto);
        return ResponseEntity.ok().body(updatedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookOutputDto> getBookById(@PathVariable Long id) {
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
    public ResponseEntity<Object> deleteBookById(@PathVariable Long id) throws ResourceNotFoundException {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Boek met id " + id + " is verwijderd!");
    }
}
