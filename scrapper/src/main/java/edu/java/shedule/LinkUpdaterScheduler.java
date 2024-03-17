package edu.java.shedule;

import edu.java.clients.BotClient;
import edu.java.service.LinkAndChatService;
import edu.java.service.LinkService;
import edu.java.service.dto.ChatDto;
import edu.java.service.dto.LinkDto;
import edu.java.service.handlersOfApi.ApiHandler;
import edu.java.service.handlersOfApi.HandleResult;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;

@Log4j2
public class LinkUpdaterScheduler {
    private BotClient client;
    private LinkService linkService;
    private LinkAndChatService linkAndChatService;
    private ApiHandler handler;

    @SuppressWarnings("checkstyle:MagicNumber") @Scheduled(fixedDelayString = "#{@scheduler.interval}")
    public int update() {
        log.trace("updating");
        List<LinkDto> links = linkService.getLinksLastCheckedBefore(10);
        int updatedCount = 0;
        for (var link : links) {
            HandleResult result = handler.handle(link);
            if (result.updates()) {
                updatedCount++;

                client.update(
                    link.getLinkId(),
                    link.getUrl(),
                    result.info(),
                    linkAndChatService.allChatsByLinkId(link.getLinkId())
                        .stream()
                        .map(ChatDto::getChatId)
                        .toArray(Long[]::new)
                );

                linkService.updateCreation(link.getLinkId(), link.getLastRegistered());
            }
            linkService.updateUpdating(link.getLinkId(), OffsetDateTime.now());
        }

        return updatedCount;
    }
}
