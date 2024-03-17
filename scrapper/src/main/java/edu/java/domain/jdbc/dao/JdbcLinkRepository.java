package edu.java.domain.jdbc.dao;

import edu.java.domain.jdbc.dto.Link;
import edu.java.service.dto.LinkDto;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JdbcLinkRepository {
    private final JdbcTemplate template;

    public Link add(Link link) {
        template.update(
            """
                INSERT INTO link (url)
                                    SELECT ?
                                    WHERE NOT EXISTS (
                                        SELECT 1
                                        FROM link
                                        WHERE url = ?
                                    );
                """,
            link.getLink(), link.getLink()
        );

        return findByUrl(link.getLink()).get();
    }

    public int remove(String url) {
        return template.update("DELETE FROM link WHERE url = ?", url);
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals") public List<Link> findAll() {
        return template.query("SELECT * FROM link", (rs, row) -> new Link(
            rs.getLong("linkId"),
            rs.getString("url"),
            rs.getObject("created_at", OffsetDateTime.class),
            rs.getObject("updated_at", OffsetDateTime.class)));
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public Optional<Link> findByUrl(String url) {
        try {
            return Optional.ofNullable(
                template.queryForObject(
                    "SELECT * FROM link WHERE url = ?",
                    (rs, row) -> new Link(
                        rs.getLong("linkId"),
                        rs.getString("url"),
                        rs.getObject("created_at", OffsetDateTime.class),
                        rs.getObject("updated_at", OffsetDateTime.class)
                    ),
                    url
                )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<Link> findById(long linkId) {
        try {
            return Optional.ofNullable(
                template.queryForObject(
                    "SELECT * FROM link WHERE linkId = ?",
                    (rs, row) -> new Link(
                        rs.getLong("linkId"),
                        rs.getString("url"),
                        rs.getObject("created_at", OffsetDateTime.class),
                        rs.getObject("updated_at", OffsetDateTime.class)
                    ),
                    linkId
                )
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<LinkDto> getLinksInOrderByUpdateTime(int amount) {
        return template.query("SELECT * FROM link ORDER BY updated_at LIMIT ?",
            (rs, row) -> new LinkDto(rs.getLong("linkId"),
                rs.getString("url"),
                rs.getObject("created_at", OffsetDateTime.class),
                rs.getObject("updated_at", OffsetDateTime.class)),
            amount);
    }

    public void updateLink(Link link) {
        template.update("UPDATE link SET created_at = ?, updated_at = ? WHERE link_id = ?",
            link.getLastCreated(), link.getLastUpdated(), link.getLinkId());
    }

}
