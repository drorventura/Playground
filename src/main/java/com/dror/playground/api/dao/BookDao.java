package com.dror.playground.api.dao;

import com.dror.playground.api.models.Book;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BookDao
{
    void addBook(Book book);
    Book getBookById(UUID id);
    Book getBookByAuthorsLastNameAndTitle(String authorsLastName, String title);
    void removeBook(UUID id);
    void decreaseQuantity(UUID bookId);
    void increaseQuantity(UUID bookId);
}
