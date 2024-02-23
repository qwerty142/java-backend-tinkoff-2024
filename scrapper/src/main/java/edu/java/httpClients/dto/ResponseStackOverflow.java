package edu.java.httpClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record ResponseStackOverflow(
    @JsonProperty("title") String title,
    @JsonProperty("last_activity_date")OffsetDateTime lastUpdated
    ) {
}
