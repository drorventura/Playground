package com.dror.playground.api.controllers;

import com.dror.playground.api.managers.BorrowManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 1:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BorrowController
{
    private BorrowManager borrowManager;

    @Transactional
    @RequestMapping(value = "borrow/personId/{personId}/bookId/{bookId}",method = RequestMethod.GET)
    public @ResponseBody
    void borrowBook(@PathVariable UUID personId, @PathVariable UUID bookId)
    {
        borrowManager.borrowBook(personId,bookId);
    }

    @Transactional
    @RequestMapping (value = "return/personId/{personId}/bookId/{bookId}",method = RequestMethod.GET)
    public @ResponseBody void returnBook(@PathVariable UUID personId, @PathVariable UUID bookId)
    {
        borrowManager.returnBook(personId, bookId);
    }

    public BorrowManager getBorrowManager()
    {
        return borrowManager;
    }

    public void setBorrowManager(BorrowManager borrowManager)
    {
        this.borrowManager = borrowManager;
    }
}
