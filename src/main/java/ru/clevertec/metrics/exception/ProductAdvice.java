package ru.clevertec.metrics.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductAdvice {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public Error handle(EntityNotFoundException e) {
        return new Error(e.getLocalizedMessage());
    }

    public record Error(String message) {
    }
}
