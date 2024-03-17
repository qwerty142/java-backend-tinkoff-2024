package edu.java.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class LinkDto {
    private long linkId;
    private String url;
    private OffsetDateTime lastRegistered;
    private OffsetDateTime lastUpdated;
}
