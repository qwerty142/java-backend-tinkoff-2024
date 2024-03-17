package edu.java.domain.jdbc.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Link {
    long linkId;
    String link;
    OffsetDateTime lastCreated;
    OffsetDateTime lastUpdated;

}
