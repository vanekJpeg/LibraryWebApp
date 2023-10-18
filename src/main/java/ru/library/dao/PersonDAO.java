package ru.library.dao;

import ru.library.models.Book;
import ru.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},new PersonMapper()).stream().findAny().orElse(null);
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name,age) VALUES(?,?)",person.getName(),person.getAge());
    }
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET  name=?, age=? WHERE id =?",updatedPerson.getName(),updatedPerson.getAge(),id);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id =?",id);
    }


    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM books  WHERE person_id=?", new Object[]{id} ,new BookMapper());
    }
}
