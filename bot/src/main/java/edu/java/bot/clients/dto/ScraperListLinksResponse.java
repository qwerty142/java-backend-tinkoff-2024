package edu.java.bot.clients.dto;

public record ScraperListLinksResponse(
    ScraperLinkResponse[] links,
    int size
) {
}
