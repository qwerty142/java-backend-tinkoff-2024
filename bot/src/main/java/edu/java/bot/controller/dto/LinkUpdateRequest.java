package edu.java.bot.controller.dto;

public record LinkUpdateRequest(
    Long id,
    String url,
    String description,
    Long[] chatIds
) {
}
