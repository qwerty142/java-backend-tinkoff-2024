package edu.java.controllers;

import edu.java.dtoRequest.ScraperAddLinkRequest;
import edu.java.dtoRequest.ScraperRemoveLinkRequest;
import edu.java.dtoResponse.ScraperLinkResponse;
import edu.java.dtoResponse.ScraperListLinksResponse;
import edu.java.mapers.LinkMapper;
import edu.java.service.LinkAndChatService;
import edu.java.service.LinkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/links")
@AllArgsConstructor
public class ScrapperLinkController {
    private LinkService linkService;
    private LinkMapper mapper;
    private LinkAndChatService linkAndChatService;

    @GetMapping
    public ScraperListLinksResponse getAllLinks(
        @RequestHeader("Tg-Chat-Id") long id
    ) {
        ScraperLinkResponse[] list = linkAndChatService.allLinksByChatId(id)
            .stream()
            .map(mapper::linkToLinkResponse)
            .toArray(ScraperLinkResponse[]::new);
        return new ScraperListLinksResponse(list, list.length);
    }

    @PostMapping
    public ScraperLinkResponse addLink(
        @RequestHeader("Tg-Chat-Id") long id,
        @RequestBody ScraperAddLinkRequest request
    ) {
        return mapper.linkToLinkResponse(
            linkService.add(id, request.link())
        );
    }

    @DeleteMapping
    public ScraperLinkResponse deleteLink(
        @RequestHeader("Tg-Chat-Id") long id,
        @RequestBody ScraperRemoveLinkRequest request
    ) {
        return mapper.linkToLinkResponse(
            linkService.remove(id, request.link())
        );
    }
}
