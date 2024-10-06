package com.learning.quizapp.controller;


import com.learning.quizapp.model.Question;
import com.learning.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/all-questions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

}
