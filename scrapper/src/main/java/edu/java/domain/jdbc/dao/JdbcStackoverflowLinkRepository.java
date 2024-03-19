package edu.java.domain.jdbc.dao;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class JdbcStackoverflowLinkRepository {
    private JdbcTemplate template;

    @Transactional
    public Optional<StackoverflowLink> findById(Long id) {
        try {
            return Optional.ofNullable(template.queryForObject(
                "SELECT * FROM linkStackoverflow WHERE link = ?",
                (rs, row) -> new StackoverflowLink(
                    rs.getLong("link"),
                    rs.getInt("commentsAmount"),
                    rs.getInt("answersAmount"),
                    rs.getBoolean("answered")
                ),
                id
            ));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Transactional
    public void update(StackoverflowLink link) {
        template.update(
            """
                INSERT INTO linkStackoverflow(link, commentsAmount, answersAmount, answered)
                VALUES (?, ?, ?, ?)
                ON CONFLICT (link) DO UPDATE
                SET commentsAmount = ?, answersAmount = ?, answered = ?
                """,
            link.getId(), link.getAmountComment(), link.getAmountAnswers(), link.getStatus(),
            link.getAmountComment(), link.getAmountAnswers(), link.getStatus()
        );
    }
}
