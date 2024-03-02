package edu.java.bot.controller.dto;

public record ApiErrorResponse(String description,
                               String code,
                               String exceptionName,
                               String exceptionMessage,
                               String[] stacktrace) {
}
