package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class TestRunnerServiceImpl implements TestRunnerService {

    private final StudentService studentService;
    private final TestService testService;
    private final ResultService resultService;
    private final LocalizedIOService ioService;

    public TestRunnerServiceImpl(StudentService studentService,
                                 TestService testService,
                                 ResultService resultService,
                                 LocalizedIOService ioService) {
        this.studentService = studentService;
        this.testService = testService;
        this.resultService = resultService;
        this.ioService = ioService;
    }

    @Override
    public void run() {
        ioService.printLocalized("app.title");
        ioService.printLine("");
        var student = studentService.askStudentCredentials();
        var testResult = testService.executeTestFor(student);
        resultService.showResult(testResult);
    }
}
