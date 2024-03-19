package edu.java.httpClients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StackoverlowLinkDto {
    private Long id;
    private Integer amountComments;
    private Integer amountAnswers;
    private Boolean status;
}
