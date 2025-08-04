package com.emb.quiz_service.service;

import com.emb.quiz_service.feign.QuizInterface;
import com.emb.quiz_service.model.QuestionResponse;
import com.emb.quiz_service.model.Quiz;
import com.emb.quiz_service.model.dto.QuestionWrapper;
import com.emb.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
//        List<Question> questions = qnRepository.findRandomQuestionsByCategory(category, numQ);
        // Calling question.service
        // http://localhost:8080/question/generate?category=Python&numberOfQuestions=5

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionsIds();

        return  quizInterface.getQuestionsById(questionIds);
    }

    public ResponseEntity<String> submitQuiz(Integer id, List<QuestionResponse> responses) {

        return quizInterface.getScore(responses);
    }
}
