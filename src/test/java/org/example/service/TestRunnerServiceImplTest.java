package org.example.service;

import org.example.domain.Student;
import org.example.domain.TestResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestRunnerServiceImplTest {

    @Test
    @DisplayName("Should ask student credentials, execute test and show result")
    void shouldRunFullTestingFlow() {
        RecordingIOService ioService = new RecordingIOService();
        Student student = new Student("Ivan", "Ivanov");
        TestResult testResult = new TestResult(student, 4, 5);

        boolean[] studentAsked = {false};
        boolean[] testExecuted = {false};
        boolean[] resultShown = {false};

        StudentService studentService = () -> {
            studentAsked[0] = true;
            return student;
        };
        TestService testService = currentStudent -> {
            testExecuted[0] = true;
            return testResult;
        };
        ResultService resultService = result -> resultShown[0] = true;

        TestRunnerService runner = new TestRunnerServiceImpl(
                studentService, testService, resultService, ioService
        );

        runner.run();

        assertTrue(studentAsked[0]);
        assertTrue(testExecuted[0]);
        assertTrue(resultShown[0]);
        assertTrue(ioService.getPrintedLines().contains("Student testing application"));
    }
}
