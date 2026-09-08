package org.example.service;

import org.example.domain.Answer;
import org.example.domain.Question;

import java.util.List;

public class TestRunnerServiceImpl implements TestRunnerService {

    private final QuestionService questionService;
    private final IOService ioService;

    public TestRunnerServiceImpl(QuestionService questionService, IOService ioService) {
        this.questionService = questionService;
        this.ioService = ioService;
    }

    @Override
    public void run() {
        List<Question> questions = questionService.findAll();
        ioService.printLine("Student testing application");
        ioService.printLine("Questions:");
        ioService.printLine("");

        int questionNumber = 1;
        for (Question question : questions) {
            printQuestion(questionNumber++, question);
        }
    }

    private void printQuestion(int questionNumber, Question question) {
        ioService.printFormattedLine("%d. %s", questionNumber, question.getText());
        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
            ioService.printFormattedLine("   %d) %s", i + 1, answers.get(i).getText());
        }
        ioService.printLine("");
    }
}
