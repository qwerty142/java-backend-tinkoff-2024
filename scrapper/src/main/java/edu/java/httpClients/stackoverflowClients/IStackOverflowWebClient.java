package edu.java.httpClients.stackoverflowClients;

import edu.java.httpClients.dto.ResponseStackOverflow;

public interface IStackOverflowWebClient {
    ResponseStackOverflow fetch(long id);
}
