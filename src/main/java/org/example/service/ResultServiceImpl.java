package org.example.service;

import org.example.config.AppProperties;
import org.example.domain.TestResult;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    private final IOService ioService;
    private final AppProperties appProperties;

    public ResultServiceImpl(IOService ioService, AppProperties appProperties) {
        this.ioService = ioService;
        this.appProperties = appProperties;
    }

    @Override
    public void showResult(TestResult testResult) {
        int questionsCount = testResult.getQuestionsCount();
        int rightAnswersCount = testResult.getRightAnswersCount();
        int requiredRightAnswers = requiredRightAnswers(questionsCount);
        boolean passed = rightAnswersCount >= requiredRightAnswers;

        ioService.printLine("Test result:");
        ioService.printFormattedLine("Student: %s", testResult.getStudent().getFullName());
        ioService.printFormattedLine("Correct answers: %d of %d", rightAnswersCount, questionsCount);
        ioService.printFormattedLine("Required to pass: %d of %d (%d%%)",
                requiredRightAnswers, questionsCount, appProperties.getPassingScore());
        ioService.printFormattedLine("Status: %s", passed ? "PASSED" : "FAILED");
    }

    private int requiredRightAnswers(int questionsCount) {
        return (int) Math.ceil(questionsCount * appProperties.getPassingScore() / 100.0);
    }
}
