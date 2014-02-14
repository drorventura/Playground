package com.dror.playground.api.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaygroundException extends RuntimeException
{
    public PlaygroundException(String message)
    {
        super(message);
    }

    public PlaygroundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
