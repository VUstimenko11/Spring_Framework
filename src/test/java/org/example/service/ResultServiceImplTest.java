package org.example.service;

import org.example.config.AppProperties;
import org.example.domain.Student;
import org.example.domain.TestResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultServiceImplTest {

    @Test
    @DisplayName("Should print PASSED when student reaches required score")
    void shouldPrintPassedWhenScoreIsEnough() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOService ioService = new StreamsIOService(new PrintStream(output, true, StandardCharsets.UTF_8), System.in);
        AppProperties appProperties = new AppProperties("questions.csv", 80);
        ResultService resultService = new ResultServiceImpl(ioService, appProperties);

        resultService.showResult(new TestResult(new Student("Ivan", "Ivanov"), 4, 5));

        String printed = output.toString(StandardCharsets.UTF_8);
        assertTrue(printed.contains("PASSED"));
        assertTrue(printed.contains("Ivan Ivanov"));
        assertTrue(printed.contains("Correct answers: 4 of 5"));
    }

    @Test
    @DisplayName("Should print FAILED when student does not reach required score")
    void shouldPrintFailedWhenScoreIsNotEnough() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOService ioService = new StreamsIOService(new PrintStream(output, true, StandardCharsets.UTF_8), System.in);
        AppProperties appProperties = new AppProperties("questions.csv", 80);
        ResultService resultService = new ResultServiceImpl(ioService, appProperties);

        resultService.showResult(new TestResult(new Student("Petr", "Petrov"), 3, 5));

        String printed = output.toString(StandardCharsets.UTF_8);
        assertTrue(printed.contains("FAILED"));
    }
}
