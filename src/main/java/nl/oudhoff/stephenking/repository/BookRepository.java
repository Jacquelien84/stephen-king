package nl.oudhoff.stephenking.repository;


import nl.oudhoff.stephenking.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
