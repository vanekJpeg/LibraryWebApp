package ru.library.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class Book {

    private int id;
    private  Integer personId;
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String author;
    @Max(value = 2024, message = "Year should be less than 2024")
    private int year;

    public Book() {
    }

    public Book( String name, String author,int personId, int year) {
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
    public boolean isFree(){
        return this.getPersonId()==0;
    }
}
