package org.example.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class QuestionReadExceptionTest {

    @Test
    @DisplayName("Should keep message and cause")
    void shouldKeepMessageAndCause() {
        RuntimeException cause = new RuntimeException("io");
        QuestionReadException withCause = new QuestionReadException("failed", cause);
        QuestionReadException messageOnly = new QuestionReadException("invalid line");

        assertEquals("failed", withCause.getMessage());
        assertSame(cause, withCause.getCause());
        assertEquals("invalid line", messageOnly.getMessage());
    }
}
