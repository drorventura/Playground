package com.dror.playground.api.managers;

import com.dror.playground.api.models.Book;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/21/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BookManager
{
    Book addBook(Book book);
    Book getBook(UUID id);
    void removeBook(UUID id);
    void decreaseQuantity(UUID bookId);
    void increaseQuantity(UUID bookId);
}
