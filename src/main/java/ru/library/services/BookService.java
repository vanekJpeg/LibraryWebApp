package ru.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.repositories.BooksRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository bookRepository;
    @Autowired
    public BookService(BooksRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public Book findOne(int id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
    @Transactional
    public void setFree(int id){
        Book book=bookRepository.findById(id).orElse(null);
        book.setOwner(null);
    }
    public List<Book> findBooksByOwner(Person person){
        return bookRepository.findBooksByOwner(person);
    }

}
