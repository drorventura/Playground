package com.dror.playground.api.managers;

import com.dror.playground.api.dao.PersonDao;
import com.dror.playground.api.exceptions.PlaygroundItemNotFound;
import com.dror.playground.api.models.Person;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonManagerImpl implements PersonManager
{
    private PersonDao personDao;

//    private PersonRepository personRepository;

    @Override
    public Person addPerson(Person person)
    {
        personDao.addPerson(person);
        return person;
    }

    @Override
    public Person getPerson(UUID id)
    {
        try
        {
//            Person person = personRepository.findOne(id);
            Person person = personDao.getPerson(id);
            return person;
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new PlaygroundItemNotFound("Person " + id + " was not found");
        }
    }

    @Override
    public void removePerson(UUID id)
    {
        try
        {
            personDao.removePerson(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new PlaygroundItemNotFound("Could not delete person " + id, e);
        }
    }

    @Override
    public void decreaseBalance(UUID personId)
    {
        personDao.decreaseBalance(personId);
    }

    @Override
    public void increaseBalance(UUID personId)
    {
        personDao.increaseBalance(personId);
    }

    public PersonDao getPersonDao()
    {
        return personDao;
    }

    @Required
    public void setPersonDao(PersonDao personDao)
    {
        this.personDao = personDao;
    }
}
