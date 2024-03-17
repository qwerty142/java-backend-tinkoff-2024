package edu.java.service;

import edu.java.service.dto.ChatDto;
import edu.java.service.dto.LinkDto;
import java.util.List;

public interface LinkAndChatService {
    List<LinkDto> allLinksByChatId(long chatId);

    List<ChatDto> allChatsByLinkId(long linkId);
}
