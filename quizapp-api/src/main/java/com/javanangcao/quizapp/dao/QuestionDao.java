package com.javanangcao.quizapp.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javanangcao.quizapp.model.Question;

import java.util.List;

@Repository
public class QuestionDao {

    private final JdbcTemplate jdbcTemplate;

    public QuestionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM question";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Question question = new Question();
            question.setId(rs.getInt("id"));
            question.setQuestionTitle(rs.getString("question_title"));
            question.setOption1(rs.getString("option1"));
            question.setOption2(rs.getString("option2"));
            question.setOption3(rs.getString("option3"));
            question.setOption4(rs.getString("option4"));
            question.setRightAnswer(rs.getString("right_answer"));
            question.setCategory(rs.getString("category"));
            return question;
        });
    }

    public List<String> findAllCategories(){
        String sql = "SELECT DISTINCT category FROM question";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return rs.getString("category");
        });
    }

    public List<Question> findByCategory(String category) {
        String sql = "SELECT * FROM question WHERE category = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Question question = new Question();
            question.setId(rs.getInt("id"));
            question.setQuestionTitle(rs.getString("question_title"));
            question.setOption1(rs.getString("option1"));
            question.setOption2(rs.getString("option2"));
            question.setOption3(rs.getString("option3"));
            question.setOption4(rs.getString("option4"));
            question.setRightAnswer(rs.getString("right_answer"));
            question.setCategory(rs.getString("category"));
            return question;
        }, category);
    }

    public void save(Question question) {
        String sql = "INSERT INTO question (question_title, option1, option2, option3, option4, right_answer, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4(), question.getRightAnswer(), question.getCategory());
        }
        catch(DataAccessException e){
            System.err.println("Error inserting question: " + e.getMessage());
        }
    }

    public List<Question> findRandomQuestionsByCategory(String category, int numQ) {
        String sql = "SELECT TOP (?) * FROM question WHERE category = ? ORDER BY NEWID()";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Question question = new Question();
            question.setId(rs.getInt("id"));
            question.setQuestionTitle(rs.getString("question_title"));
            question.setOption1(rs.getString("option1"));
            question.setOption2(rs.getString("option2"));
            question.setOption3(rs.getString("option3"));
            question.setOption4(rs.getString("option4"));
            question.setRightAnswer(rs.getString("right_answer"));
            question.setCategory(rs.getString("category"));
            return question;
        }, numQ, category);
    }

    public void delete(int id) {
        // Bước 1: Xóa các bản ghi liên kết trong bảng quiz_questions
        String deleteQuizQuestionSql = "DELETE FROM quiz_questions WHERE questions_id = ?";
        try {
            jdbcTemplate.update(deleteQuizQuestionSql, id);
        } catch (DataAccessException e) {
            System.err.println("Error deleting from quiz_questions: " + e.getMessage());
        }

        // Bước 2: Lấy tất cả các quiz_id từ bảng quiz
        String getAllQuizIdsSql = "SELECT id FROM quiz";
        List<Integer> quizIds = jdbcTemplate.queryForList(getAllQuizIdsSql, Integer.class);

        // Bước 3: Kiểm tra xem các quiz_id này có tồn tại trong quiz_questions không.
        // Nếu không tồn tại trong quiz_questions, xóa quiz
        for (Integer quizId : quizIds) {
            String checkIfQuizHasQuestionsSql = "SELECT COUNT(*) FROM quiz_questions WHERE quiz_id = ?";
            int count = jdbcTemplate.queryForObject(checkIfQuizHasQuestionsSql, Integer.class, quizId);

            if (count == 0) {
                // Nếu quiz không có câu hỏi liên kết, xóa quiz đó
                String deleteQuizSql = "DELETE FROM quiz WHERE id = ?";
                try {
                    jdbcTemplate.update(deleteQuizSql, quizId);
                } catch (DataAccessException e) {
                    System.err.println("Error deleting quiz: " + e.getMessage());
                }
            }
        }

        // Bước 4: Xóa câu hỏi trong bảng question
        String deleteQuestionSql = "DELETE FROM question WHERE id = ?";
        try {
            jdbcTemplate.update(deleteQuestionSql, id);
        } catch (DataAccessException e) {
            System.err.println("Error deleting question: " + e.getMessage());
        }
    }    
}