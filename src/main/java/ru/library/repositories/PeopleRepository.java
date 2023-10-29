package ru.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.library.models.Person;

public interface PeopleRepository extends JpaRepository<Person,Integer> {
}
