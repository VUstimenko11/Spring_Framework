package org.example.domain;

public class TestResult {

    private final Student student;
    private final int rightAnswersCount;
    private final int questionsCount;

    public TestResult(Student student, int rightAnswersCount, int questionsCount) {
        this.student = student;
        this.rightAnswersCount = rightAnswersCount;
        this.questionsCount = questionsCount;
    }

    public Student getStudent() {
        return student;
    }

    public int getRightAnswersCount() {
        return rightAnswersCount;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }
}
