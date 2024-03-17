package edu.java.dtoResponse;

public record ScraperListLinksResponse(
    ScraperLinkResponse[] links,
    int size
) {
}
