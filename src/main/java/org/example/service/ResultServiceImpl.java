package org.example.service;

import org.example.config.AppProperties;
import org.example.domain.TestResult;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService {

    private final LocalizedIOService ioService;
    private final AppProperties appProperties;

    public ResultServiceImpl(LocalizedIOService ioService, AppProperties appProperties) {
        this.ioService = ioService;
        this.appProperties = appProperties;
    }

    @Override
    public void showResult(TestResult testResult) {
        int questionsCount = testResult.getQuestionsCount();
        int rightAnswersCount = testResult.getRightAnswersCount();
        int requiredRightAnswers = requiredRightAnswers(questionsCount);
        boolean passed = rightAnswersCount >= requiredRightAnswers;
        String status = ioService.getMessage(passed ? "result.passed" : "result.failed");

        ioService.printLocalized("result.header");
        ioService.printLocalized("result.student", testResult.getStudent().getFullName());
        ioService.printLocalized("result.correct", rightAnswersCount, questionsCount);
        ioService.printLocalized("result.required",
                requiredRightAnswers, questionsCount, appProperties.getPassingScore());
        ioService.printLocalized("result.status", status);
    }

    private int requiredRightAnswers(int questionsCount) {
        return (int) Math.ceil(questionsCount * appProperties.getPassingScore() / 100.0);
    }
}
