package org.example.shell;

import org.example.domain.Student;
import org.example.domain.TestResult;
import org.example.service.ResultService;
import org.example.service.StudentContext;
import org.example.service.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.shell.Availability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCommandsTest {

    private StudentContext studentContext;
    private boolean[] testExecuted;
    private boolean[] resultShown;
    private TestCommands testCommands;

    @BeforeEach
    void setUp() {
        studentContext = new StudentContext();
        testExecuted = new boolean[]{false};
        resultShown = new boolean[]{false};

        TestService testService = student -> {
            testExecuted[0] = true;
            return new TestResult(student, 4, 5);
        };
        ResultService resultService = result -> resultShown[0] = true;

        testCommands = new TestCommands(studentContext, testService, resultService);
    }

    @Test
    @DisplayName("Should login student and make start available")
    void shouldLoginStudent() {
        String message = testCommands.login("Ivan", "Ivanov");

        assertEquals("Student logged in: Ivan Ivanov", message);
        assertTrue(studentContext.isLoggedIn());
        assertEquals(new Student("Ivan", "Ivanov"), studentContext.getStudent());
        assertTrue(testCommands.startAvailability().isAvailable());
    }

    @Test
    @DisplayName("Should not allow start before login")
    void shouldNotAllowStartBeforeLogin() {
        Availability availability = testCommands.startAvailability();

        assertFalse(availability.isAvailable());
        assertTrue(availability.getReason().contains("login"));
    }

    @Test
    @DisplayName("Should start test for logged in student")
    void shouldStartTestForLoggedInStudent() {
        testCommands.login("Ivan", "Ivanov");

        String message = testCommands.start();

        assertEquals("Testing finished for Ivan Ivanov", message);
        assertTrue(testExecuted[0]);
        assertTrue(resultShown[0]);
    }
}
