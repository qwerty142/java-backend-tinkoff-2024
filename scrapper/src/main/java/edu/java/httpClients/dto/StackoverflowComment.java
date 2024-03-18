package edu.java.httpClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record StackoverflowComment(
    @JsonProperty("creation_date")OffsetDateTime creation
    ) {
}
