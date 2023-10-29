package ru.library.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.repositories.PeopleRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    public List<Book> getBooksByPersonId(int id){
        Optional<Person> person = peopleRepository.findById(id);
        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());
            person.get().getBooks().forEach(book -> {
                long diffTime = Math.abs(book.getOwnedAt().getTime()-new Date().getTime());
                if(diffTime>864000000)
                    book.setExpired(true);
             });
            return person.get().getBooks();
        }
        else return Collections.emptyList();
    }
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}

