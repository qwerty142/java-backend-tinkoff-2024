package edu.java.exceptions;

import edu.java.dtoResponse.ScraperApiErrorResponse;
import jakarta.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ScrapperExceptionHandle {
    @ExceptionHandler({
        MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class,
        ConstraintViolationException.class,
        MethodArgumentTypeMismatchException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ScraperApiErrorResponse createResponse(Exception e, WebRequest request, HttpStatus status) {
        return new ScraperApiErrorResponse(
            request.getDescription(false),
            status.toString(),
            e.getClass().getName(),
            e.getMessage(),
            Arrays.stream(e.getStackTrace()).map(Objects::toString).toArray(String[]::new)
        );
    }
}
