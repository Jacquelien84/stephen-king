package nl.oudhoff.stephenking.service;

import nl.oudhoff.stephenking.dto.input.BookInputDto;
import nl.oudhoff.stephenking.dto.mapper.BookMapper;
import nl.oudhoff.stephenking.dto.output.BookOutputDto;
import nl.oudhoff.stephenking.exception.ResourceNotFoundException;
import nl.oudhoff.stephenking.model.Book;
import nl.oudhoff.stephenking.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public BookOutputDto createBook(BookInputDto bookInputDto) {
        Book model = BookMapper.fromInputDtoToModel(bookInputDto);
        bookRepo.save(model);
        return BookMapper.fromModelToOutputDto(model);
    }

    // Er lijkt niets te gebeuren. Hier naar kijken
    public BookOutputDto updateBook(long id, BookInputDto bookInputDto) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Boek met id " + id + " niet gevonden"));
        Book updatedBook = bookRepo.save(book);

        return BookMapper.fromModelToOutputDto(updatedBook);
    }

    public BookOutputDto getBookById(long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Boek met id " + id + " niet gevonden"));
        return BookMapper.fromModelToOutputDto(book);
    }

    public BookOutputDto getBookByTitle(String title) {
        Book book = bookRepo.findByTitleIgnoreCase(title).
                orElseThrow(() -> new ResourceNotFoundException("Boek met titel " + title + " niet gevonden"));

        return BookMapper.fromModelToOutputDto(book);
    }

    public List<BookOutputDto> getAllBooks() {
        List<Book> allBooks = bookRepo.findAll();
        List<BookOutputDto> allBooksOutputDtoList = new ArrayList<>();

        for (Book book : allBooks) {
            allBooksOutputDtoList.add(BookMapper.fromModelToOutputDto(book));
        }
        return allBooksOutputDtoList;
    }

    public void deleteBookById(long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            bookRepo.delete(book.get());
        } else {
            throw new ResourceNotFoundException("Boek met id " + id + " niet gevonden");
        }
    }
}
