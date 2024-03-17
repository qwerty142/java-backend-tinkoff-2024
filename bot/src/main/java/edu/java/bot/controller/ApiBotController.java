package edu.java.bot.controller;

import edu.java.bot.controller.dto.LinkUpdateRequest;
import edu.java.bot.controller.dto.LinkUpdateResponse;
import edu.java.bot.service.LinkUpdateHandle;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
public class ApiBotController {
    private LinkUpdateHandle handle;

    @PostMapping
    public LinkUpdateResponse update(@RequestBody LinkUpdateRequest request) {
        handle.handle(request.url(), request.description(), request.chatIds());
        return new LinkUpdateResponse();
    }
}
