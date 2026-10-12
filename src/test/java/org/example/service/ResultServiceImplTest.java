package org.example.service;

import org.example.domain.Student;
import org.example.domain.TestResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultServiceImplTest {

    @Test
    @DisplayName("Should print PASSED when student reaches required score")
    void shouldPrintPassedWhenScoreIsEnough() {
        RecordingIOService recordingIOService = new RecordingIOService();
        LocalizedIOService ioService = TestLocalizedIOFactory.create(recordingIOService);
        ResultService resultService = new ResultServiceImpl(ioService, TestLocalizedIOFactory.properties());

        resultService.showResult(new TestResult(new Student("Ivan", "Ivanov"), 4, 5));

        List<String> printed = recordingIOService.getPrintedLines();
        assertTrue(printed.stream().anyMatch(line -> line.contains("PASSED")));
        assertTrue(printed.stream().anyMatch(line -> line.contains("Ivan Ivanov")));
        assertTrue(printed.stream().anyMatch(line -> line.contains("Correct answers: 4 of 5")));
    }

    @Test
    @DisplayName("Should print FAILED when student does not reach required score")
    void shouldPrintFailedWhenScoreIsNotEnough() {
        RecordingIOService recordingIOService = new RecordingIOService();
        LocalizedIOService ioService = TestLocalizedIOFactory.create(recordingIOService);
        ResultService resultService = new ResultServiceImpl(ioService, TestLocalizedIOFactory.properties());

        resultService.showResult(new TestResult(new Student("Petr", "Petrov"), 3, 5));

        assertTrue(recordingIOService.getPrintedLines().stream().anyMatch(line -> line.contains("FAILED")));
    }
}
