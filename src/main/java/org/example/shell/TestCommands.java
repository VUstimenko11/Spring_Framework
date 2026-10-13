package org.example.shell;

import org.example.domain.Student;
import org.example.domain.TestResult;
import org.example.service.ResultService;
import org.example.service.StudentContext;
import org.example.service.TestService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class TestCommands {

    private final StudentContext studentContext;
    private final TestService testService;
    private final ResultService resultService;

    public TestCommands(StudentContext studentContext,
                        TestService testService,
                        ResultService resultService) {
        this.studentContext = studentContext;
        this.testService = testService;
        this.resultService = resultService;
    }

    @ShellMethod(value = "Login student by first and last name", key = {"login", "l"})
    public String login(@ShellOption(value = {"first-name", "firstName"}, help = "Student first name") String firstName,
                        @ShellOption(value = {"last-name", "lastName"}, help = "Student last name") String lastName) {
        studentContext.login(firstName, lastName);
        return "Student logged in: " + studentContext.getStudent().getFullName();
    }

    @ShellMethod(value = "Start student testing", key = {"start", "s"})
    @ShellMethodAvailability("startAvailability")
    public String start() {
        Student student = studentContext.getStudent();
        TestResult testResult = testService.executeTestFor(student);
        resultService.showResult(testResult);
        return "Testing finished for " + student.getFullName();
    }

    public Availability startAvailability() {
        return studentContext.isLoggedIn()
                ? Availability.available()
                : Availability.unavailable("please login first using 'login' command");
    }
}
