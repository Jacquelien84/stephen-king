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

    public BookOutputDto updateBook(long id, BookInputDto bookInputDto) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            Book model = BookMapper.fromInputDtoToModel(bookInputDto);
            bookRepo.save(model);
            return BookMapper.fromModelToOutputDto(model);
        } else {
            throw new ResourceNotFoundException("Boek met id " + id + " niet gevonden");
        }
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
        List<Book> books = bookRepo.findAll();
        List<BookOutputDto> booksOutputDtoList = new ArrayList<>();

        for (Book book : books) {
            booksOutputDtoList.add(BookMapper.fromModelToOutputDto(book));
        }
        return booksOutputDtoList;
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
