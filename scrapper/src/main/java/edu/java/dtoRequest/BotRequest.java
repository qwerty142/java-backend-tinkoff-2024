package edu.java.dtoRequest;

public record BotRequest(
    long id,
    String url,
    String description,
    Long[] ids
) {
}
