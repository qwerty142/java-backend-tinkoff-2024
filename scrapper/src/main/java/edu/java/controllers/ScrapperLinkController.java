package edu.java.controllers;

import edu.java.dtoRequest.ScraperAddLinkRequest;
import edu.java.dtoRequest.ScraperRemoveLinkRequest;
import edu.java.dtoResponse.ScraperLinkResponse;
import edu.java.dtoResponse.ScraperListLinksResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/links")
public class ScrapperLinkController {
    @GetMapping
    public ScraperListLinksResponse getAllLinks(
        @RequestHeader("Tg-Chat-Id") long id
    ) {
        ScraperLinkResponse[] list = new ScraperLinkResponse[0];
        return new ScraperListLinksResponse(list, 0);
    }

    @PostMapping
    public ScraperLinkResponse addLink(
        @RequestHeader("Tg-Chat-Id") long id,
        @RequestBody ScraperAddLinkRequest request
    ) {
        return new ScraperLinkResponse(id, request.link());
    }

    @DeleteMapping
    public ScraperLinkResponse deleteLink(
        @RequestHeader("Tg-Chat-Id") long id,
        @RequestBody ScraperRemoveLinkRequest request
    ) {
        return new ScraperLinkResponse(id, request.link());
    }
}
