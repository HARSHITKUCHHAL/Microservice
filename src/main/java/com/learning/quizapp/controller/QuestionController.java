package com.learning.quizapp.controller;


import com.learning.quizapp.model.Question;
import com.learning.quizapp.service.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/all-questions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("create-question")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete-question/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        try {
            questionService.deleteQuestionById(id);
            return ResponseEntity.ok("Question with ID " + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("update-question/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Integer id, @RequestBody Question updatedQuestion){
        try {
            Question updated=questionService.updateQuestionById(id, updatedQuestion);
            return ResponseEntity.ok(updated);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
