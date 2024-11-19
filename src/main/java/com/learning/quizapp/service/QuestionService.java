package com.learning.quizapp.service;


import com.learning.quizapp.dao.QuestionDao;
import com.learning.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public void deleteQuestionById(Integer id) {
        if (!questionDao.existsById(id)){
            return;
        }
        questionDao.deleteById(id);
    }

    public Question updateQuestionById(Integer id, Question updatedQuestion) {
        Optional<Question> existingQuestionOpt = questionDao.findById(id);
        if (existingQuestionOpt.isEmpty()){
            throw new IllegalArgumentException("Question with ID " + id + " does not exist.");
        }
        Question existingQuestion = existingQuestionOpt.get();

        // Update fields
        existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
        existingQuestion.setOption1(updatedQuestion.getOption1());
        existingQuestion.setOption2(updatedQuestion.getOption2());
        existingQuestion.setOption3(updatedQuestion.getOption3());
        existingQuestion.setOption4(updatedQuestion.getOption4());
        existingQuestion.setRightAnswer(updatedQuestion.getRightAnswer());
        existingQuestion.setDifficultylevel(updatedQuestion.getDifficultylevel());
        existingQuestion.setCategory(updatedQuestion.getCategory());

        // Save and return the updated question
        return questionDao.save(existingQuestion);
    }
}
