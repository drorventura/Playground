package com.dror.playground.api.dao;

import com.dror.playground.api.models.Person;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:13 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PersonDao
{
    void addPerson(Person person);
    Person getPerson(UUID id);
    void removePerson(UUID id);
    void decreaseBalance(UUID personId);
    void increaseBalance(UUID personId);
}
