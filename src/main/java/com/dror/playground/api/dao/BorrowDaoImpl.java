package com.dror.playground.api.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class BorrowDaoImpl implements BorrowDao
{
    private SimpleJdbcTemplate simpleJdbcTemplate;

    public BorrowDaoImpl(DataSource dataSource)
    {
        simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public void returnBook(UUID personId, UUID bookId)
    {
        int numOfRemovedBorrows = simpleJdbcTemplate.update("DELETE FROM transactions " +
                                                            "WHERE person_id = ? AND book_id = ?", personId, bookId);
        if (numOfRemovedBorrows == 0)
        {
            throw new EmptyResultDataAccessException(1);
        }
    }

    @Override
    public void borrowBook(UUID personId, UUID bookId)
    {
        simpleJdbcTemplate.update("INSERT INTO transactions (borrow_id, person_id, book_id, transaction_date)" +
                                  "VALUES (?, ?, ?, ?)", UUID.randomUUID(),personId,bookId,new Date());
    }


    private class UUIDRowMapper implements RowMapper<UUID>
    {
        @Override
        public UUID mapRow(ResultSet resultSet, int i) throws SQLException
        {
            String borrowIdAsString = resultSet.getString("borrow_id");
            UUID borrowId = UUID.fromString(borrowIdAsString);
            return borrowId;
        }
    }
}
