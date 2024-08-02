package nl.oudhoff.stephenking.controller;

import jakarta.validation.Valid;
import nl.oudhoff.stephenking.dto.BookDto;
import nl.oudhoff.stephenking.model.Book;
import nl.oudhoff.stephenking.repository.BookRepository;
import nl.oudhoff.stephenking.service.BookService;
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

    private final BookService service;
    public BookController(BookService bookService) {
        this.service = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());

        @PostMapping
        public ResponseEntity<?> createBook (@Valid @RequestBody BookDto bookDto, BindingResult br){
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
                bookDto = service.createBook(bookDto);
                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + bookDto.id).toUriString());
                return ResponseEntity.created(uri).body(bookDto);
            } catch (Exception ex) {
                return ResponseEntity.unprocessableEntity().body("Creation failed");
            }
        }
    }
    }
