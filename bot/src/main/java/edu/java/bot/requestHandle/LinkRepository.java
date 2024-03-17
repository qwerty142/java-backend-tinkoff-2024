package edu.java.bot.requestHandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LinkRepository implements ILinkProvider, ILinkTrack, ILinkUntrack, ILinkRegister {
    private Map<Long, List<String>> userLinksMap = new HashMap<>();

    @Override
    public List<String> getLinks(long tgChatId) {
        return userLinksMap.getOrDefault(tgChatId, new ArrayList<>());
    }

    @Override
    public void track(long tgChatId, String link) {
        userLinksMap.putIfAbsent(tgChatId, new ArrayList<>());
        userLinksMap.get(tgChatId).add(link);
    }

    @Override
    public void unTrack(long tgChatId, String link) {
        userLinksMap.get(tgChatId).remove(link);
    }

    @Override
    public void register(Long id) {
        userLinksMap.putIfAbsent(id, new ArrayList<>());
    }
}
