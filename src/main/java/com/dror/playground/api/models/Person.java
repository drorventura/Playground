package com.dror.playground.api.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 10:44 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Person
{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    private String firstName = "Batz";
    private String lastName = "Almighty";

    private String streetAddress = "Batz Street, 17";
    private String cityStateAddress = "Batz Land";

    private int maxNumOfBorrows = 5;
    private int totalAmountOfBooksLeft = 20;

    @ManyToMany
    private Collection<Book> books;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityStateAddress() {
        return cityStateAddress;
    }

    public void setCityStateAddress(String cityStateAddress) {
        this.cityStateAddress = cityStateAddress;
    }

    public int getMaxNumOfBorrows() {
        return maxNumOfBorrows;
    }

    public void setMaxNumOfBorrows(int maxNumOfBorrows) {
        this.maxNumOfBorrows = maxNumOfBorrows;
    }

    public int getTotalAmountOfBooksLeft() {
        return totalAmountOfBooksLeft;
    }

    public void setTotalAmountOfBooksLeft(int totalAmountOfBooksLeft) {
        this.totalAmountOfBooksLeft = totalAmountOfBooksLeft;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
