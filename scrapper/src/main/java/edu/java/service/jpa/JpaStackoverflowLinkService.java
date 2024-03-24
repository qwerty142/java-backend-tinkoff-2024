package edu.java.service.jpa;

import edu.java.domain.jpa.dao.JpaStackoverflowLinkRepository;
import edu.java.httpClients.dto.StackoverlowLinkDto;
import edu.java.mapers.StackOverflowLinkMapper;
import edu.java.service.StackOverflowLinkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class JpaStackoverflowLinkService implements StackOverflowLinkService {

    private final JpaStackoverflowLinkRepository stackoverflowLinkRepository;
    private final StackOverflowLinkMapper linkMapper;
    @Override
    public Optional<StackoverlowLinkDto> findById(Long id) {
        return stackoverflowLinkRepository.findById(id).map(linkMapper::jpaToDto);
    }

    @Override
    public void update(StackoverlowLinkDto link) {
        stackoverflowLinkRepository.save(linkMapper.dtoToJpa(link));
    }
}
