package com.dror.playground.api.controllers;

import com.dror.playground.api.managers.BookManager;
import com.dror.playground.api.models.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/books")
public class BookController
{
    private BookManager bookManager;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Book addBook(@RequestBody Book book)
    {
        Book createdBook = bookManager.addBook(book);
        return createdBook;
    }

    @RequestMapping (value = "{bookId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Book getBook(@PathVariable UUID bookId)
    {
        return bookManager.getBook(bookId);
    }

    @RequestMapping (value = "{bookId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable UUID bookId)
    {
        bookManager.removeBook(bookId);
    }

    @RequestMapping (value = "{bookId}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Book updateBook(@PathVariable UUID id,@RequestBody Book book)
    {
        book.setBookId(id);
        bookManager.addBook(book);

        return book;
    }

    public BookManager getBookManager() {
        return bookManager;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }
}
