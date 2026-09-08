package org.example.domain;

import java.util.Objects;

public class Answer {

    private final String text;
    private final boolean correct;

    public Answer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer = (Answer) o;
        return correct == answer.correct && Objects.equals(text, answer.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, correct);
    }
}
