package com.javanangcao.quizapp.model;

import lombok.Data;
import java.util.List;

@Data
public class Result {
    private Integer mark;
    private double percent;
    private List<Integer> correctQuestionIds;
}