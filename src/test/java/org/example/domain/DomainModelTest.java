package org.example.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DomainModelTest {

    @Test
    @DisplayName("Answer should expose text, correctness and equality")
    void shouldSupportAnswerEquality() {
        Answer answer = new Answer("Java", true);
        Answer same = new Answer("Java", true);
        Answer other = new Answer("Python", false);

        assertEquals("Java", answer.getText());
        assertTrue(answer.isCorrect());
        assertEquals(answer, same);
        assertEquals(answer.hashCode(), same.hashCode());
        assertNotEquals(answer, other);
        assertNotEquals(answer, null);
        assertEquals(answer, answer);
    }

    @Test
    @DisplayName("Question should expose text, answers and equality")
    void shouldSupportQuestionEquality() {
        Question question = new Question("Q?", List.of(new Answer("A", true)));
        Question same = new Question("Q?", List.of(new Answer("A", true)));
        Question other = new Question("Other?", List.of(new Answer("B", false)));

        assertEquals("Q?", question.getText());
        assertEquals(1, question.getAnswers().size());
        assertEquals(question, same);
        assertEquals(question.hashCode(), same.hashCode());
        assertNotEquals(question, other);
        assertFalse(question.equals("Q?"));
    }

    @Test
    @DisplayName("Student should expose names and equality")
    void shouldSupportStudentEquality() {
        Student student = new Student("Ivan", "Ivanov");
        Student same = new Student("Ivan", "Ivanov");
        Student other = new Student("Petr", "Petrov");

        assertEquals("Ivan", student.getFirstName());
        assertEquals("Ivanov", student.getLastName());
        assertEquals("Ivan Ivanov", student.getFullName());
        assertEquals(student, same);
        assertEquals(student.hashCode(), same.hashCode());
        assertNotEquals(student, other);
    }

    @Test
    @DisplayName("TestResult should expose counters and student")
    void shouldExposeTestResultFields() {
        Student student = new Student("Ivan", "Ivanov");
        TestResult result = new TestResult(student, 4, 5);

        assertEquals(student, result.getStudent());
        assertEquals(4, result.getRightAnswersCount());
        assertEquals(5, result.getQuestionsCount());
    }
}
