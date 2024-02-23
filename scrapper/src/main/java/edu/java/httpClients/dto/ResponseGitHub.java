package edu.java.httpClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record ResponseGitHub(
    @JsonProperty("title") String title,
    @JsonProperty("updated_at")OffsetDateTime lastUpdated
    ) {
}
