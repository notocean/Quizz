package com.javanangcao.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import com.javanangcao.quizapp.model.Question;
import com.javanangcao.quizapp.service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController {

    //object of the service class QuestionService
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>>getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionCategory(@PathVariable String category){
        return questionService.getAllQuestionsByCategory(category);
    }

    @GetMapping("allCategories")
    public ResponseEntity<List<String>> getAllCategories(){
        return questionService.getAllCategories();
    }
    

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        try {
            questionService.deleteQuestion(id);
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
