package org.example;

import org.example.config.AppProperties;
import org.example.dao.QuestionDao;
import org.example.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationContextTest {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private MessageSource messageSource;

    @Test
    @DisplayName("Should load Spring Boot context with test configuration")
    void shouldLoadTestConfiguration() {
        assertEquals(Locale.ENGLISH, appProperties.getLocale());
        assertEquals("questions-test", appProperties.getTest().getFileName());
        assertEquals(80, appProperties.getPassingScore());
        assertEquals("questions-test_en.csv", appProperties.resolveQuestionsResourceName());

        List<Question> questions = questionDao.findAll();
        assertEquals(2, questions.size());
        assertFalse(questions.get(0).getAnswers().isEmpty());

        assertNotNull(messageSource.getMessage("app.title", null, Locale.ENGLISH));
    }
}
