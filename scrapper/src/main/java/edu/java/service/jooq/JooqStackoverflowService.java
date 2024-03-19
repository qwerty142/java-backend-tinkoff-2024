package edu.java.service.jooq;

import edu.java.domain.jooq.dao.JooqStackoverflowRepository;
import edu.java.httpClients.dto.StackoverlowLinkDto;
import edu.java.mapers.StackOverflowLinkMapper;
import edu.java.service.StackOverflowLinkService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
public class JooqStackoverflowService implements StackOverflowLinkService {
    private JooqStackoverflowRepository stackoverflowRepository;
    private StackOverflowLinkMapper mapper;

    @Override
    public Optional<StackoverlowLinkDto> findById(Long id) {
        return stackoverflowRepository.findById(id).map(mapper::jooqToDto);
    }

    @Override
    public void update(StackoverlowLinkDto link) {
        stackoverflowRepository.upsertLink(mapper.dtoToJooq(link));
    }
}
