package org.example.service;

import org.example.domain.Answer;
import org.example.domain.Question;
import org.example.domain.Student;
import org.example.domain.TestResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestServiceImplTest {

    @Test
    @DisplayName("Should count correct answers entered by student")
    void shouldCountCorrectAnswers() {
        String input = String.join(System.lineSeparator(), "2", "1") + System.lineSeparator();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        IOService ioService = new StreamsIOService(
                new PrintStream(new ByteArrayOutputStream(), true, StandardCharsets.UTF_8),
                inputStream
        );
        QuestionService questionService = () -> List.of(
                new Question("2 + 2?", List.of(
                        new Answer("3", false),
                        new Answer("4", true)
                )),
                new Question("Is Java compiled?", List.of(
                        new Answer("Yes", true),
                        new Answer("No", false)
                ))
        );
        TestService testService = new TestServiceImpl(ioService, questionService);

        TestResult result = testService.executeTestFor(new Student("Ivan", "Ivanov"));

        assertEquals(2, result.getRightAnswersCount());
        assertEquals(2, result.getQuestionsCount());
    }
}
