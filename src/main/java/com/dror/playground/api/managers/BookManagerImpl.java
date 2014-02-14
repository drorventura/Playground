package com.dror.playground.api.managers;

import com.dror.playground.api.dao.BookDao;
import com.dror.playground.api.exceptions.PlaygroundItemNotFound;
import com.dror.playground.api.models.Book;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/21/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookManagerImpl implements BookManager
{
    private BookDao bookDao;

    @Override
    public Book addBook(Book book)
    {
        bookDao.addBook(book);
        return book;
    }

    @Override
    public Book getBook(UUID id)
    {
        try
        {
            Book book = bookDao.getBookById(id);
            return  book;
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new PlaygroundItemNotFound("book " + id + " was not found",e);
        }
    }

    @Override
    public void removeBook(UUID id)
    {
        try
        {
            bookDao.removeBook(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new PlaygroundItemNotFound("could not delete book " + id, e);
        }
    }

    @Override
    public void decreaseQuantity(UUID bookId)
    {
        bookDao.decreaseQuantity(bookId);
    }

    @Override
    public void increaseQuantity(UUID bookId)
    {
        bookDao.increaseQuantity(bookId);
    }

    public BookDao getBookDao()
    {
        return bookDao;
    }

    public void setBookDao(BookDao bookDao)
    {
        this.bookDao = bookDao;
    }
}
