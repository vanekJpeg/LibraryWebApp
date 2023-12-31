package ru.library.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Column(name = "name")
    private String name;
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Column(name = "author")
    private String author;
    @Max(value = 2024, message = "Year should be less than 2024")
    @Column(name = "year")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person owner;
    @Column(name = "owned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ownedAt;
    @Transient
    private boolean expired;
    public Date getOwnedAt() {
        return ownedAt;
    }
    public void setOwnedAt(Date ownedAt) {
        this.ownedAt = ownedAt;
    }
    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    public Book() {
    }
    public Book( String name, String author, int year) {
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
    public boolean isFree(){
        return owner == null;
    }
    public boolean isExpired(){
        return expired;
    }
    public void setExpired(boolean isExpired){
        if(isExpired) this.expired=true;
    }
}
