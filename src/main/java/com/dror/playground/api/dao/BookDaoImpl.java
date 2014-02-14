package com.dror.playground.api.dao;

import com.dror.playground.api.exceptions.PlaygroundCannotBorrow;
import com.dror.playground.api.models.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class BookDaoImpl implements BookDao
{
    private SimpleJdbcTemplate simpleJdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public BookDaoImpl(DataSource dataSource)
    {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public void addBook(Book book)
    {
//        simpleJdbcTemplate.update("INSERT INTO library(book_id,title,authors_last_name,authors_first_name,quantity) " +
//                                  "VALUES (?, ?, ?, ?, ?)", book.getBookId(),book.getTitle(),book.getAuthorsLastName(),book.getAuthorsFirstName(),book.getQuantity());
        entityManager.persist(book);
    }

//    2b8358a9-251f-464c-bfef-c77a3340d020


    @Override
    public Book getBookById(UUID id)
    {
//        Book book = simpleJdbcTemplate.queryForObject("SELECT * " +
//                                                      "FROM library " +
//                                                      "WHERE book_id = ?", new BookRowMapper(), id);
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    @Override
    public Book getBookByAuthorsLastNameAndTitle(String authorsLastName, String title)
    {
        Book book = simpleJdbcTemplate.queryForObject("SELECT title, authors_last_name " +
                                                      "FROM library " +
                                                      "WHERE title = ?, authors_last_name = ?", new BookRowMapper(), title, authorsLastName);
        return book;
    }

    @Override
    public void removeBook(UUID id)
    {
        int numberOfRemovedPeople = simpleJdbcTemplate.update("DELETE FROM library " +
                                                              "WHERE book_id = ?", id);
        if (numberOfRemovedPeople == 0)
        {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @Override
    public void decreaseQuantity(UUID bookId)
    {
        int numOfUpdatedRecords = simpleJdbcTemplate.update("UPDATE library " +
                                                            "SET quantity = quantity - 1 " +
                                                            "WHERE book_id = ? AND quantity > 0", bookId);
        if (numOfUpdatedRecords == 0)
        {
            throw new PlaygroundCannotBorrow("No more books: " + bookId + " has left in library");
        }
    }

    @Override
    public void increaseQuantity(UUID bookId)
    {
        simpleJdbcTemplate.update("UPDATE library " +
                                  "SET quantity = quantity + 1 " +
                                  "WHERE book_id = ?", bookId);
    }

    private class BookRowMapper implements RowMapper<Book>
    {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException
        {
            Book book = new Book();

            String bookIdAsString = resultSet.getString("book_id");
            UUID bookId = UUID.fromString(bookIdAsString);
            book.setBookId(bookId);

            String title = resultSet.getString("title");
            book.setTitle(title);

            String authorsLastName = resultSet.getString("authors_last_name");
            book.setAuthorsLastName(authorsLastName);

            String authorsFirstName = resultSet.getString("authors_first_name");
            book.setAuthorsFirstName(authorsFirstName);

            int quantity = resultSet.getInt("quantity");
            book.setQuantity(quantity);

            return book;
        }
    }
}
