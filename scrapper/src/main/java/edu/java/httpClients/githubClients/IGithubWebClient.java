package edu.java.httpClients.githubClients;

import edu.java.httpClients.dto.ResponseGitHub;
import lombok.NonNull;

public interface IGithubWebClient {
    ResponseGitHub fetch(@NonNull String user, @NonNull String repository);
}
