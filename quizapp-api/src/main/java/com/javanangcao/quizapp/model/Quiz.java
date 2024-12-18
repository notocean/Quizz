package com.javanangcao.quizapp.model;

import lombok.Data;

import java.util.List;

@Data
public class Quiz {
    private Integer id;
    private String title;
    private List<Question> questions; 
}