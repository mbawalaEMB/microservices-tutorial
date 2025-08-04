package com.emb.question_service.service;

import com.emb.question_service.dto.QuestionWrapper;
import com.emb.question_service.mapper.QuestionMapper;
import com.emb.question_service.model.Question;
import com.emb.question_service.model.QuestionResponse;
import com.emb.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository qnRepository;

    @Autowired
    QuestionMapper qnMapper;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(qnRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return ResponseEntity.ok(qnRepository.findByCategory(category));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            qnRepository.save(question);
            return  new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Could not create the question!", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuiz(String category, Integer numberOfQuestions) {
        List<Integer> listQns = new ArrayList<>();
        try {
            listQns = (List<Integer>) qnRepository.findRandomQuestionsByCategory(category, numberOfQuestions);
            return new ResponseEntity<>(listQns, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(listQns, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionIds) {
        List<QuestionWrapper> listQns = new ArrayList<>();
        try {
            listQns = qnRepository.findAllById(questionIds)
                    .stream().map(qnMapper::toDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(listQns, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(listQns, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getScore(List<QuestionResponse> responses) {
        try {
            int correct = 0;
            for (QuestionResponse qnResponse : responses) {
                Question question = qnRepository.findById(qnResponse.getId()).get();
                if (qnResponse.getId().equals(question.getId())
                        && qnResponse.getAnswer().equals(question.getRightAnswer())) {
                    correct++;
                }
            }

            Double result = (((double) correct/responses.size()) * 100);

            return new ResponseEntity<>(result + "%", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("An error occurred and could not calculate score.", HttpStatus.BAD_REQUEST);
    }
}
