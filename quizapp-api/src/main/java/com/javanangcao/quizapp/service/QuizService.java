package com.javanangcao.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.javanangcao.quizapp.dao.QuestionDao;
import com.javanangcao.quizapp.dao.QuizDao;
import com.javanangcao.quizapp.model.Question;
import com.javanangcao.quizapp.model.QuestionWrapper;
import com.javanangcao.quizapp.model.Quiz;
import com.javanangcao.quizapp.model.Response;
import com.javanangcao.quizapp.model.Result;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.getQuestions();

        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Result> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id);
        List<Question> questions = quiz.getQuestions();

        int totalQuestions = questions.size();
        int mark = 0;
        List<Integer> correctQuestionIds = new ArrayList<>();
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                mark++;
                correctQuestionIds.add(questions.get(i).getId());
            }
            i++;
        }

        double percent = ((double) mark / totalQuestions) * 100;
        Result result = new Result();
        result.setMark(mark);
        result.setPercent(percent);
        result.setCorrectQuestionIds(correctQuestionIds);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        try {
            return new ResponseEntity<>(quizDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public void deleteQuiz(Integer id) {
        Quiz quiz = quizDao.findById(id);
        if (quiz != null) {
            quizDao.delete(quiz);
        } else {
            throw new RuntimeException("Quiz not found");
        }
    }
}