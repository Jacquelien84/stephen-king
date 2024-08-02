package nl.oudhoff.stephenking.service;

import nl.oudhoff.stephenking.dto.BookDto;
import nl.oudhoff.stephenking.exception.ResourceNotFoundException;
import nl.oudhoff.stephenking.model.Book;
import nl.oudhoff.stephenking.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepos;

    public BookService(BookRepository bookRepos) {
        this.bookRepos = bookRepos;
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.id);
        book.setTitle(bookDto.title);
        book.setAuthor(bookDto.author);
        book.setOriginalTitle(bookDto.originalTitle);
        book.setReleased(bookDto.released);
        book.setMovieAdaptation(bookDto.movieAdaptation);
        book.setDescription(bookDto.description);

        this.bookRepos.save(book);
        return bookDto;
    }

    public BookDto getBookById(long id) {
        Book book = this.bookRepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        BookDto bookDto = new BookDto();
        bookDto.id = book.getId();
        bookDto.title = book.getTitle();
        bookDto.author = book.getAuthor();
        bookDto.originalTitle = book.getOriginalTitle();
        bookDto.released = book.getReleased();
        bookDto.movieAdaptation = book.getMovieAdaptation();
        bookDto.description = book.getDescription();
        return bookDto;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = this.bookRepos.findAll();
        List<BookDto> bookDtos = new ArrayList<>();

        for (Book book : books) {
            BookDto bookDto = new BookDto();

            bookDto.id = book.getId();
            bookDto.title = book.getTitle();
            bookDto.author = book.getAuthor();
            bookDto.originalTitle = book.getOriginalTitle();
            bookDto.released = book.getReleased();
            bookDto.movieAdaptation = book.getMovieAdaptation();
            bookDto.description = book.getDescription();

            bookDtos.add(bookDto);
        }
        return bookDtos;
    }
}
