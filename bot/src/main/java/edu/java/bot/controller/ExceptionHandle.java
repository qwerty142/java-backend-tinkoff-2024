package edu.java.bot.controller;

import edu.java.bot.controller.dto.ApiErrorResponse;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        MethodArgumentNotValidException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleRequest(Exception exception, WebRequest request) {
        return new ApiErrorResponse(
            request.getDescription(false),
            HttpStatus.BAD_REQUEST.toString(),
            exception.getClass().getName(),
            exception.getMessage(),
            Arrays.stream(exception.getStackTrace()).map(Objects::toString).toArray(String[]::new)
        );
    }
}
