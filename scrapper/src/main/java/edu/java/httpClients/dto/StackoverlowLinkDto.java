package edu.java.httpClients.dto;

import lombok.Data;

@Data
public class StackoverlowLinkDto {
    private Long id;
    private Integer amountComments;
    private Integer amountAnswers;
    private Boolean status;
}
