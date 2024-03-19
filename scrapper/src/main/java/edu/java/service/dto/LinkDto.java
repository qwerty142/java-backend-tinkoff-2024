package edu.java.service.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkDto {
    private long linkId;
    private String url;
    private OffsetDateTime lastRegistered;
    private OffsetDateTime lastUpdated;
}
