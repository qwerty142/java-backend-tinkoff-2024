package edu.java.service;

import edu.java.service.dto.LinkDto;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

public interface LinkService {
    LinkDto add(long tgChatId, String url);

    LinkDto remove(long tgChatId, String url);

    Collection<LinkDto> listAll(long tgChatId);

    List<LinkDto> getLinksLastCheckedBefore(int amount);

    void updateCreation(long linkId, OffsetDateTime time);

    void updateUpdating(long linkId, OffsetDateTime time);
}
