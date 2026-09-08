package org.example;

import org.example.config.AppConfig;
import org.example.config.AppProperties;
import org.example.dao.QuestionDao;
import org.example.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringJUnitConfig(AppConfig.class)
class ApplicationContextTest {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private QuestionDao questionDao;

    @Test
    @DisplayName("Should load test properties and questions from test resources")
    void shouldLoadTestConfiguration() {
        assertEquals("questions-test.csv", appProperties.getTestFileName());
        assertEquals(80, appProperties.getPassingScore());

        List<Question> questions = questionDao.findAll();
        assertEquals(2, questions.size());
        assertFalse(questions.get(0).getAnswers().isEmpty());
    }
}
