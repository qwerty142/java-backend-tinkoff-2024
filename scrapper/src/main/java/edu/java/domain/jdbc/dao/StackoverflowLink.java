package edu.java.domain.jdbc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StackoverflowLink {
    private Long id;
    private Integer amountComment;
    private Integer amountAnswers;
    private Boolean status;
}
