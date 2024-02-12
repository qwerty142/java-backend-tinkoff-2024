package edu.java.bot.requestHandle;

import java.util.List;

public interface ILinkProvider {
    List<String> getLinks(long tgChatId);
}
