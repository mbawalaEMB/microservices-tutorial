package com.emb.question_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_title", length = 255)
    private String questionTitle;

    @Column(name = "option1", length = 255)
    private String option1;

    @Column(name = "option2", length = 255)
    private String option2;

    @Column(name = "option3", length = 255)
    private String option3;

    @Column(name = "option4", length = 255)
    private String option4;

    @Column(name = "right_answer", length = 255)
    private String rightAnswer;

    @Column(name = "difficulty_level", length = 50)
    private String difficultyLevel;

    @Column(name = "category", length = 50)
    private String category;

//    @ManyToMany(mappedBy = "questions")
//    private List<Quiz> quizzes;

}
