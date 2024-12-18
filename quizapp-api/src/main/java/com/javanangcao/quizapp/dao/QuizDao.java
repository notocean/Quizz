package com.javanangcao.quizapp.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javanangcao.quizapp.model.Question;
import com.javanangcao.quizapp.model.Quiz;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizDao {

    private final JdbcTemplate jdbcTemplate;

    public QuizDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Quiz quiz) {
        String sql = "INSERT INTO quiz (title) VALUES (?)";
        jdbcTemplate.update(sql, quiz.getTitle());

        String getLastIdSql = "SELECT MAX(id) FROM quiz";
        Integer quizId = jdbcTemplate.queryForObject(getLastIdSql, Integer.class);
        quiz.setId(quizId);
        
        String quizQuestionSql = "INSERT INTO quiz_questions (quiz_id, questions_id) VALUES (?, ?)";
            
        // Lặp qua các câu hỏi và lưu chúng vào bảng quiz_question
        for (Question question : quiz.getQuestions()) {
            // Liên kết quiz và câu hỏi trong bảng quiz_question
            jdbcTemplate.update(quizQuestionSql, quizId, question.getId());
        }
    }

    public List<Quiz> findAll() {
        String sql = "SELECT * FROM quiz";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Quiz quiz = new Quiz();
            quiz.setId(rs.getInt("id"));
            quiz.setTitle(rs.getString("title"));
            
            return quiz;
        });
    }

    public Quiz findById(Integer id) {
        String sql = "SELECT * FROM quiz WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Quiz quiz = new Quiz();
            quiz.setId(rs.getInt("id"));
            quiz.setTitle(rs.getString("title"));

            String questionIdsSql = "SELECT questions_id FROM quiz_questions WHERE quiz_id = ?";
            List<Integer> questionIds = jdbcTemplate.queryForList(questionIdsSql, Integer.class, quiz.getId());

            List<Question> questions = new ArrayList<>();
            String questionSql = "SELECT * FROM question WHERE id = ?";
            for (Integer questionId : questionIds) {
                questions.add(jdbcTemplate.queryForObject(questionSql, (rs1, rowNum1) -> {
                    Question question = new Question();
                    question.setId(rs1.getInt("id"));
                    question.setQuestionTitle(rs1.getString("question_title"));
                    question.setOption1(rs1.getString("option1"));
                    question.setOption2(rs1.getString("option2"));
                    question.setOption3(rs1.getString("option3"));
                    question.setOption4(rs1.getString("option4"));
                    question.setRightAnswer(rs1.getString("right_answer"));
                    question.setCategory(rs1.getString("category"));
                    return question;
                }, questionId));
            }
            quiz.setQuestions(questions); // Gán danh sách câu hỏi vào quiz
            return quiz;
        }, id);
    }

    public void delete(Quiz quiz) {
        Integer quizId = quiz.getId();
        
        String quizQuestionsSql = "DELETE FROM quiz_questions WHERE quiz_id = ?";
        jdbcTemplate.update(quizQuestionsSql, quizId);

        String quizSql = "DELETE FROM quiz WHERE id = ?";
        jdbcTemplate.update(quizSql, quizId);
    }
}