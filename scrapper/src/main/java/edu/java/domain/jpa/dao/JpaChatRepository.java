package edu.java.domain.jpa.dao;

import edu.java.domain.jpa.dto.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByLinksLinkId(Long linkId);
}
