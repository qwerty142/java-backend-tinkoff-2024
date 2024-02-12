package edu.java.bot.requestHandle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LinkRepository implements ILinkProvider, ILinkTrack, ILinkUntrack, ILinkRegister{
    private Map<Long, List<String>> user_links_map = new HashMap<>();
    @Override
    public List<String> getLinks(long tgChatId) {
        return user_links_map.getOrDefault(tgChatId, new ArrayList<>());
    }

    @Override
    public void track(long tgChatId, String link) {
        user_links_map.putIfAbsent(tgChatId, new ArrayList<>());
        user_links_map.get(tgChatId).add(link);
    }

    @Override
    public void unTrack(long tgChatId, String link) {
        user_links_map.get(tgChatId).remove(link);
    }

    @Override
    public void register(Long id) {
        user_links_map.putIfAbsent(id, new ArrayList<>());
    }
}
