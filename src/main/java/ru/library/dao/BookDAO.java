package ru.library.dao;

import ru.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.library.models.Person;

import java.util.List;
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BookMapper());
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id},new BookMapper()).stream().findAny().orElse(null);
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Books(name,author,person_id,year) VALUES(?,?,?,?)",book.getName(),book.getAuthor(),null,book.getYear());
    }
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Books SET  name=?,author=?,person_id=?, year=? WHERE id =?",updatedBook.getName(),updatedBook.getAuthor(),updatedBook.getPersonId(),updatedBook.getYear(),id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Books WHERE id =?",id);
    }

    public void setFree(int id){
        jdbcTemplate.update("UPDATE Books SET  person_id=? WHERE id =?",null,id);
    }
    public Person getOwner(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",new Object[]{show(id).getPersonId()},new PersonMapper()).stream().findAny().orElse(null);
    }
}
