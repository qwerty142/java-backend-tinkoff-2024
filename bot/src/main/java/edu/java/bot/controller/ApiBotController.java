package edu.java.bot.controller;

import edu.java.bot.controller.dto.LinkUpdateRequest;
import edu.java.bot.controller.dto.LinkUpdateResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
public class ApiBotController {
    @PostMapping
    public void update(@RequestBody LinkUpdateRequest request) {
        return;
    }
}
