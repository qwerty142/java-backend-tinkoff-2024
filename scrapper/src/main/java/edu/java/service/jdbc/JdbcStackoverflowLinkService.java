package edu.java.service.jdbc;

import edu.java.domain.jdbc.dao.JdbcStackoverflowLinkRepository;
import edu.java.httpClients.dto.StackoverlowLinkDto;
import edu.java.mapers.StackOverflowLinkMapper;
import edu.java.service.StackOverflowLinkService;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JdbcStackoverflowLinkService implements StackOverflowLinkService {
    private JdbcStackoverflowLinkRepository repository;
    private StackOverflowLinkMapper mapper;

    @Override
    public Optional<StackoverlowLinkDto> findById(Long id) {
        return repository.findById(id).map(mapper::jdbcToDto);
    }

    @Override
    public void update(StackoverlowLinkDto link) {
        repository.update(mapper.dtoToJdbc(link));
    }
}
