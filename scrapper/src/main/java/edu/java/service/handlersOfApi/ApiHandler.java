package edu.java.service.handlersOfApi;

import edu.java.service.dto.LinkDto;

public interface ApiHandler {
    HandleResult handle(LinkDto link);

    boolean supports(String link);

    void next(ApiHandler handler);
}
