package edu.java.controllers;

import edu.java.dtoResponse.BotResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tg-chat")
public class ScrapperTgChatController {
    @PostMapping("/{id}")
    public BotResponse registerChat(
        @PathVariable("id") long id
    ) {
        return new BotResponse("", "", "", "", new String[]{});
    }

    @DeleteMapping("/{id}")
    public BotResponse deleteChat(
        @PathVariable("id") long id
    ) {
        return new BotResponse("", "", "", "", new String[]{});
    }
}
