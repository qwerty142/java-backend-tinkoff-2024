package edu.java.service.handlersOfApi;

import edu.java.httpClients.dto.ResponseGitHub;
import edu.java.httpClients.githubClients.GithubClient;
import edu.java.service.dto.LinkDto;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GithubHandler implements ApiHandler {

    private GithubClient client;
    private ApiHandler nextHandler;
    private final String ghLink = "github.com";
    private final String separator = "://";

    @Override
    public HandleResult handle(LinkDto link) {
        if (link == null) {
            return new HandleResult(null, false);
        }

        if (supports(link.getUrl())) {
            int skip = 1;
            String copyLink = link.getUrl();
            copyLink = copyLink.replace(separator, "/");
            if (!copyLink.equals(link.getUrl())) {
                skip++;
            }

            String[] linkParts = copyLink.split("/");
            List<String> extensions = List.of(Arrays.copyOfRange(linkParts, skip, linkParts.length));
            ResponseGitHub response = client.fetch(extensions.get(0), extensions.get(1));
            if (link.getLastUpdated().isBefore(response.lastUpdated())) {
                link.setLastUpdated(response.lastUpdated());
                return new HandleResult(response.title(), true);
            }
        }

        return nextHandler == null ? new HandleResult(null, false) : nextHandler.handle(link);
    }

    @Override
    public boolean supports(String link) {
        int indexProtocolSeparator = link.indexOf(separator);
        if (indexProtocolSeparator == -1) {
            indexProtocolSeparator = 0;
        } else {
            indexProtocolSeparator += separator.length();
        }

        int indexFirstPathSeparator = link.indexOf(separator, indexProtocolSeparator);
        if (indexFirstPathSeparator == -1) {
            indexFirstPathSeparator = link.length();
        }

        return link.substring(indexProtocolSeparator, indexFirstPathSeparator).equalsIgnoreCase(ghLink);
    }

    @Override
    public void next(ApiHandler handler) {
        this.nextHandler = handler;
    }
}
