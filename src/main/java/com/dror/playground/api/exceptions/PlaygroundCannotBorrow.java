package com.dror.playground.api.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaygroundCannotBorrow extends PlaygroundException
{
    public PlaygroundCannotBorrow(String message) {
        super(message);
    }

    public PlaygroundCannotBorrow(String message, Throwable cause) {
        super(message, cause);
    }
}
