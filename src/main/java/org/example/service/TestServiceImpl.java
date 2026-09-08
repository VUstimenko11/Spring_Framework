package org.example.service;

import org.example.domain.Answer;
import org.example.domain.Question;
import org.example.domain.Student;
import org.example.domain.TestResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;
    private final QuestionService questionService;

    public TestServiceImpl(IOService ioService, QuestionService questionService) {
        this.ioService = ioService;
        this.questionService = questionService;
    }

    @Override
    public TestResult executeTestFor(Student student) {
        List<Question> questions = questionService.findAll();
        int rightAnswersCount = 0;

        ioService.printLine("");
        ioService.printLine("Please answer the questions:");
        ioService.printLine("");

        int questionNumber = 1;
        for (Question question : questions) {
            if (askQuestion(questionNumber++, question)) {
                rightAnswersCount++;
            }
        }

        return new TestResult(student, rightAnswersCount, questions.size());
    }

    private boolean askQuestion(int questionNumber, Question question) {
        List<Answer> answers = question.getAnswers();
        ioService.printFormattedLine("%d. %s", questionNumber, question.getText());
        for (int i = 0; i < answers.size(); i++) {
            ioService.printFormattedLine("   %d) %s", i + 1, answers.get(i).getText());
        }

        int answerNumber = ioService.readIntForRangeWithPrompt(
                1,
                answers.size(),
                "Enter the answer number:",
                "Invalid answer number. Please try again."
        );

        ioService.printLine("");
        return answers.get(answerNumber - 1).isCorrect();
    }
}
