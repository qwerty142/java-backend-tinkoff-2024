package edu.java.domain.jpa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StackoverflowLink {
    @Id
    @Column(name = "linkId")
    private Long linkId;

    @Column(name = "commentsAmount")
    private Integer commentsAmount;

    @Column(name = "answersAmount")
    private Integer answersAmount;

    @Column(name = "answered")
    private Boolean answered;


}
