package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.library.models.Book;
import ru.library.models.Person;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book,Integer> {
    List<Book> findBooksByOwner(Person person);
}
