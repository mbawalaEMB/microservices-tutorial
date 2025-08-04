package com.emb.question_service.controller;

import com.emb.question_service.dto.QuestionWrapper;
import com.emb.question_service.model.Question;
import com.emb.question_service.model.QuestionResponse;
import com.emb.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService qnService;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {

        return qnService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> addQuestion(@PathVariable String category) {
        return qnService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return qnService.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer numberOfQuestions) {
        return qnService.getQuiz(category, numberOfQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds) {
        return qnService.getQuestionsById(questionIds);
    }

    @PostMapping("/score")
    public ResponseEntity<String> getScore(@RequestBody List<QuestionResponse> responses) {
        return qnService.getScore(responses);
    }
}
