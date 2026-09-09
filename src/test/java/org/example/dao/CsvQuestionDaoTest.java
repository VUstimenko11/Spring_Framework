package org.example.dao;

import org.example.config.AppProperties;
import org.example.domain.Answer;
import org.example.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvQuestionDaoTest {

    @Test
    @DisplayName("Should read questions from localized classpath CSV resource")
    void shouldReadQuestionsFromClasspathResource() {
        QuestionDao questionDao = new CsvQuestionDao(appProperties("questions-test", Locale.ENGLISH));

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
        QuestionDao questionDao = new CsvQuestionDao(appProperties("missing-questions", Locale.ENGLISH));

        assertThrows(QuestionReadException.class, questionDao::findAll);
    }

    @Test
    @DisplayName("Should throw when CSV line has no answers")
    void shouldThrowWhenCsvLineIsInvalid() {
        QuestionDao questionDao = new CsvQuestionDao(appProperties("invalid-questions", Locale.ENGLISH));

        assertThrows(QuestionReadException.class, questionDao::findAll);
    }

    private AppProperties appProperties(String fileName, Locale locale) {
        AppProperties properties = new AppProperties();
        properties.setLocale(locale);
        properties.getTest().setFileName(fileName);
        properties.getTest().setPassingScore(80);
        return properties;
    }
}
