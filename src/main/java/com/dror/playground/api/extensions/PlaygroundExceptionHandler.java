package com.dror.playground.api.extensions;

import com.dror.playground.api.exceptions.PlaygroundCannotBorrow;
import com.dror.playground.api.exceptions.PlaygroundException;
import com.dror.playground.api.exceptions.PlaygroundItemNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: dror
 * Date: 10/19/13
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class PlaygroundExceptionHandler
{
    @ExceptionHandler(PlaygroundException.class)
    public ResponseEntity<PlaygroundError> handle(PlaygroundException e, HttpServletRequest requst)
    {
        PlaygroundError playgroundError = new PlaygroundError();

        String className = e.getClass().getSimpleName();
        playgroundError.setType(className);

        String msg = e.getMessage();
        playgroundError.setMessage(msg);

        HttpStatus httpErrorCode;
        if (e instanceof PlaygroundItemNotFound)
        {
            httpErrorCode = HttpStatus.NOT_FOUND;
        }
        else if (e instanceof PlaygroundCannotBorrow)
        {
            httpErrorCode = HttpStatus.FORBIDDEN;
        }
        else
        {
            httpErrorCode = HttpStatus.INTERNAL_SERVER_ERROR;
        }





        ResponseEntity<PlaygroundError> responseEntity = new ResponseEntity<PlaygroundError>(playgroundError,httpErrorCode);
        return responseEntity;
    }

}
