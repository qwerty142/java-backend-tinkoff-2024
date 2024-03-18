package edu.java.httpClients.stackoverflowClients;

import edu.java.httpClients.dto.ResponseStackOverflow;
import edu.java.httpClients.dto.StackoverflowCommentResponse;

public interface IStackOverflowWebClient {
    ResponseStackOverflow fetch(long id);

    StackoverflowCommentResponse fetchComments(long id);
}
