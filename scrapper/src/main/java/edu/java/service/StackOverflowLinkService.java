package edu.java.service;

import edu.java.httpClients.dto.StackoverlowLinkDto;
import java.util.Optional;

public interface StackOverflowLinkService {
    Optional<StackoverlowLinkDto> findById(Long id);

    void update(StackoverlowLinkDto link);
}
