package edu.java.httpClients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record ResponseStackOverflow(
    @JsonProperty("title") String title,
    @JsonProperty("is_answered") Boolean status,
    @JsonProperty("answer_count") Integer amountAnswers,
    @JsonProperty("last_activity_date")OffsetDateTime lastUpdated
    ) {
}
