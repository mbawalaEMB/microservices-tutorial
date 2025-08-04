package com.emb.quiz_service.model.dto;

import lombok.Data;

@Data
public class QuizDto {

    private String title;
    private String category;
    private Integer numberOfQuestions;

}
