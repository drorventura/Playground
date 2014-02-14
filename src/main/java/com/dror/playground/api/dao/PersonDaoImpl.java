package com.dror.playground.api.dao;

import com.dror.playground.api.exceptions.PlaygroundCannotBorrow;
import com.dror.playground.api.models.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class PersonDaoImpl implements PersonDao
{
    private SimpleJdbcTemplate simpleJdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public PersonDaoImpl(DataSource dataSource)
    {
        this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public void addPerson(Person person)
    {
        entityManager.persist(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPerson(UUID id)
    {
        Person person = entityManager.find(Person.class, id);
        return person;
    }

    @Override
    public void removePerson(UUID id)
    {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);

//        int numberOfRemovedPeople = simpleJdbcTemplate.update("DELETE FROM people " +
//                                                              "WHERE id = ?", id);
//        if (numberOfRemovedPeople == 0)
//        {
//            throw new EmptyResultDataAccessException(1);
//        }
    }

    @Override
    public void decreaseBalance(UUID personId)
    {
        Query personId1 = entityManager.createQuery("UPDATE Person p " +
                                                    "SET p.maxNumOfBorrows = p.maxNumOfBorrows - 1, p.totalAmountOfBooksLeft = p.totalAmountOfBooksLeft - 1 " +
                                                    "WHERE p.id = :personId AND p.maxNumOfBorrows > 0 AND p.totalAmountOfBooksLeft > 0")
                .setParameter("personId", personId);

        int numOfUpdatedRecords = personId1.executeUpdate();

        if (numOfUpdatedRecords == 0)
        {
            throw new PlaygroundCannotBorrow("Person does not have enough balance");
        }
    }

    @Override
    public void increaseBalance(UUID personId)
    {
        simpleJdbcTemplate.update("UPDATE people " +
                                  "SET max_num_of_borrows = max_num_of_borrows + 1 " +
                                  "WHERE id = ?", personId);
    }

    private class PersonRowMapper implements RowMapper<Person>
    {
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException
        {
            Person person = new Person();

            String idAsString = resultSet.getString("id");
            UUID id = UUID.fromString(idAsString);
            person.setId(id);

            String lastName = resultSet.getString("last_name");
            person.setLastName(lastName);

            String firstName = resultSet.getString("first_name");
            person.setFirstName(firstName);

            String streetAddress = resultSet.getString("street_address");
            person.setStreetAddress(streetAddress);

            String cityStateZip = resultSet.getString("city_state_zip");
            person.setCityStateAddress(cityStateZip);

            int maxNumOfBorrows = resultSet.getInt("max_num_of_borrows");
            person.setMaxNumOfBorrows(maxNumOfBorrows);

            int totalAmountOfBooksLeft = resultSet.getInt("total_amount_of_books_left");
            person.setTotalAmountOfBooksLeft(totalAmountOfBooksLeft);

            return person;
        }
    }
}
