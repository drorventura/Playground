package com.dror.playground.api.dao;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 1:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BorrowDao
{
    void borrowBook(UUID personId, UUID bookId);
    void returnBook(UUID personId, UUID bookId);
}
