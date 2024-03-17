package edu.java.controllers;

import edu.java.dtoResponse.ScraperChatResponse;
import edu.java.service.TgChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tg-chat")
@AllArgsConstructor
public class ScrapperTgChatController {
    private TgChatService chatService;

    @PostMapping("/{id}")
    public ScraperChatResponse registerChat(
        @PathVariable("id") long id
    ) {
        chatService.register(id);
        return new ScraperChatResponse();
    }

    @DeleteMapping("/{id}")
    public ScraperChatResponse deleteChat(
        @PathVariable("id") long id
    ) {
        chatService.unregister(id);
        return new ScraperChatResponse();
    }
}
