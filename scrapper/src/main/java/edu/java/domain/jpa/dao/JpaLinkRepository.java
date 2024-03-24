package edu.java.domain.jpa.dao;

import edu.java.domain.jpa.dto.Link;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaLinkRepository extends JpaRepository<Link, Long> {
    List<Link> findAllByChatsChatId(Long chatId);
    Optional<Link> findByUrl(String url);
    List<Link> findByOrderByUpdatedAtAsc(Limit limit);

    @Modifying
    @Query(value = "DELETE FROM chatAndLink WHERE chatId = :chatId", nativeQuery = true)
    void deleteAllByChatsChatId(Long chatId);
    @Modifying
    @Query(value = "INSERT INTO chatAndLink (chatId, linkId) VALUES (:linkId, :chatId)", nativeQuery = true)
    void insertChatAndLink(Long chatId, Long linkId);

    @Modifying
    @Query(value = "DELETE FROM chatAndLink WHERE linkId = :linkId AND chatId = :chatId", nativeQuery = true)
    void removeChatAndLink(Long linkId, Long chatId);

}
