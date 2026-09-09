package org.example.dao;

import org.example.config.AppProperties;
import org.example.domain.Answer;
import org.example.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvQuestionDaoTest {

    @Test
    @DisplayName("Should read questions from classpath CSV resource")
    void shouldReadQuestionsFromClasspathResource() {
        QuestionDao questionDao = new CsvQuestionDao(new AppProperties("questions-test.csv", 80));

        List<Question> questions = questionDao.findAll();

        assertEquals(2, questions.size());
        Question first = questions.get(0);
        assertEquals("What is 2 + 2?", first.getText());
        assertEquals(3, first.getAnswers().size());
        assertTrue(first.getAnswers().stream().anyMatch(Answer::isCorrect));
        assertFalse(questions.get(1).getAnswers().isEmpty());
    }

    @Test
    @DisplayName("Should throw when CSV resource is missing")
    void shouldThrowWhenResourceIsMissing() {
        QuestionDao questionDao = new CsvQuestionDao(new AppProperties("missing-questions.csv", 80));

        assertThrows(QuestionReadException.class, questionDao::findAll);
    }

    @Test
    @DisplayName("Should throw when CSV line has no answers")
    void shouldThrowWhenCsvLineIsInvalid() {
        QuestionDao questionDao = new CsvQuestionDao(new AppProperties("invalid-questions.csv", 80));

        assertThrows(QuestionReadException.class, questionDao::findAll);
    }
}
