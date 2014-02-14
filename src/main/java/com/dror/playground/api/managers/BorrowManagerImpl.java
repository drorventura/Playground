package com.dror.playground.api.managers;

import com.dror.playground.api.dao.BorrowDao;
import com.dror.playground.api.exceptions.PlaygroundCannotBorrow;
import com.dror.playground.api.exceptions.PlaygroundException;
import com.dror.playground.api.exceptions.PlaygroundItemNotFound;
import com.dror.playground.api.models.Book;
import com.dror.playground.api.models.Person;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Collection;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/21/13
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class BorrowManagerImpl implements BorrowManager
{
    private BorrowDao borrowDao;
    private PersonManager personManager;
    private BookManager bookManager;

    @Override
    public void borrowBook(UUID personId, UUID bookId)
    {
        personManager.decreaseBalance(personId);

//      bookManager.decreaseQuantity(bookId);

        Person person = personManager.getPerson(personId);
        Book book = bookManager.getBook(bookId);

        Collection<Person> persons = book.getPersons();
        persons.add(person);


        bookManager.addBook(book);
//
//        try
//        {
//            borrowDao.borrowBook(personId,bookId);
//        }
//        catch (DuplicateKeyException e)
//        {
//            throw new PlaygroundCannotBorrow("person already has the book");
//        }
    }

    @Override
    public void returnBook(UUID personId, UUID bookId)
    {
        personManager.increaseBalance(personId);

        bookManager.increaseQuantity(bookId);

        try
        {
            borrowDao.returnBook(personId, bookId);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new PlaygroundItemNotFound("no such borrow");
        }
    }

    public BorrowDao getBorrowDao() {
        return borrowDao;
    }

    public void setBorrowDao(BorrowDao borrowDao) {
        this.borrowDao = borrowDao;
    }

    public PersonManager getPersonManager() {
        return personManager;
    }

    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    public BookManager getBookManager() {
        return bookManager;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }
}
