package edu.java.domain.jpa.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "link")
public class Link {
    @Id
    @Column(name = "linkId")
    private Long id;

    @Column(name = "url")
    private String url;

    @PastOrPresent
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @PastOrPresent
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "chatAndLink",
        joinColumns = @JoinColumn(name = "linkId"),
        inverseJoinColumns = @JoinColumn(name = "chatId")
    )
    private List<Chat> chats;

}
