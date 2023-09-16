package net.javaguides.springbootrestfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author vsushko
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {

    private final String message;

    public EmailAlreadyExistsException(String message) {
        this.message = message;
    }
}
