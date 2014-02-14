package com.dror.playground.api.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaygroundItemNotFound extends PlaygroundException
{

    public PlaygroundItemNotFound(String message) {
        super(message);
    }

    public PlaygroundItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
