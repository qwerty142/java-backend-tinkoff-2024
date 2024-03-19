package edu.java.httpClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StackoverflowCommentResponse(
    @JsonProperty("items") StackoverflowComment[] items
) {
}
