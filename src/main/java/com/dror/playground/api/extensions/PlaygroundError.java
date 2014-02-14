package com.dror.playground.api.extensions;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlaygroundError
{
    private String type;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
