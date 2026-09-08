package org.example.service;

import org.example.dao.QuestionDao;
import org.example.domain.Answer;
import org.example.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionServiceImplTest {

    @Test
    @DisplayName("Should return all questions from DAO")
    void shouldReturnAllQuestionsFromDao() {
        List<Question> expected = List.of(
                new Question("Sample question?", List.of(
                        new Answer("Yes", true),
                        new Answer("No", false)
                ))
        );
        QuestionDao questionDao = () -> expected;
        QuestionService questionService = new QuestionServiceImpl(questionDao);

        List<Question> actual = questionService.findAll();

        assertEquals(expected, actual);
    }
}
