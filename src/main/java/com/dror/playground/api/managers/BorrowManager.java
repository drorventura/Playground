package com.dror.playground.api.managers;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/21/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BorrowManager
{
    void borrowBook(UUID personId, UUID bookId);
    void returnBook(UUID personId, UUID bookId);
}
