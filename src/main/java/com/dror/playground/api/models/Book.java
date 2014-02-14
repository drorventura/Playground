package com.dror.playground.api.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Book
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private UUID bookId;

    private String title;

    private String authorsLastName = "sfsd";
    private String authorsFirstName = "sefer";
    private int quantity = 15;

    private Collection<Person> persons;

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorsLastName() {
        return authorsLastName;
    }

    public void setAuthorsLastName(String authorsLastName) {
        this.authorsLastName = authorsLastName;
    }

    public String getAuthorsFirstName() {
        return authorsFirstName;
    }

    public void setAuthorsFirstName(String authorsFirstName) {
        this.authorsFirstName = authorsFirstName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }
}
