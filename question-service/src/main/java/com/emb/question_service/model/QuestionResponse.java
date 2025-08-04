package com.emb.question_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionResponse {

    /**
     * Question id
     * */
    private Integer id;

    /**
     * The answer chosen by user for a particular question
     * */
    private String answer;

}
