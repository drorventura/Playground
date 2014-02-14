package com.dror.playground.api.controllers;

import com.dror.playground.api.managers.PersonManager;
import com.dror.playground.api.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/people")
public class PersonController
{
    private PersonManager personManager;

    @RequestMapping (value = "/",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Person addPerson(@RequestBody Person person)
    {
        Person createdPerson = personManager.addPerson(person);
        return createdPerson;
    }

    @RequestMapping (value = "{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Person getPerson(@PathVariable UUID id)
    {
        return personManager.getPerson(id);
    }

    @RequestMapping (value = "{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable UUID id)
    {
        personManager.removePerson(id);
    }

    @RequestMapping (value = "{id}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Person updatePerson(@PathVariable UUID id,@RequestBody Person person)
    {
        person.setId(id);
        personManager.addPerson(person);

        return person;
    }

    public PersonManager getPersonManager()
    {
        return personManager;
    }

    public void setPersonManager(PersonManager personManager)
    {
        this.personManager = personManager;
    }

}
