package org.example.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StreamsIOServiceTest {

    @Test
    @DisplayName("Should print line and formatted line")
    void shouldPrintLines() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        StreamsIOService ioService = new StreamsIOService(
                new PrintStream(output, true, StandardCharsets.UTF_8),
                new ByteArrayInputStream(new byte[0])
        );

        ioService.printLine("Hello");
        ioService.printFormattedLine("Value: %d", 42);

        String printed = output.toString(StandardCharsets.UTF_8);
        assertTrue(printed.contains("Hello"));
        assertTrue(printed.contains("Value: 42"));
    }

    @Test
    @DisplayName("Should read trimmed string with prompt")
    void shouldReadStringWithPrompt() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        StreamsIOService ioService = new StreamsIOService(
                new PrintStream(output, true, StandardCharsets.UTF_8),
                new ByteArrayInputStream(("  Ivan  " + System.lineSeparator()).getBytes(StandardCharsets.UTF_8))
        );

        String value = ioService.readStringWithPrompt("Name:");

        assertEquals("Ivan", value);
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Name:"));
    }

    @Test
    @DisplayName("Should read valid int after invalid attempts")
    void shouldReadValidIntAfterInvalidAttempts() {
        String input = String.join(System.lineSeparator(), "abc", "0", "3") + System.lineSeparator();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        StreamsIOService ioService = new StreamsIOService(
                new PrintStream(output, true, StandardCharsets.UTF_8),
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
        );

        int value = ioService.readIntForRangeWithPrompt(1, 4, "Enter:", "Error");

        assertEquals(3, value);
        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Error"));
    }

    @Test
    @DisplayName("Should throw when int cannot be read after max attempts")
    void shouldThrowWhenIntCannotBeRead() {
        String input = String.join(System.lineSeparator(),
                "x", "x", "x", "x", "x", "x", "x", "x", "x", "x") + System.lineSeparator();
        StreamsIOService ioService = new StreamsIOService(
                new PrintStream(new ByteArrayOutputStream(), true, StandardCharsets.UTF_8),
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))
        );

        assertThrows(IllegalArgumentException.class,
                () -> ioService.readIntForRangeWithPrompt(1, 2, "Enter:", "Error"));
    }

    @Test
    @DisplayName("Should create service with default streams")
    void shouldCreateWithDefaultStreams() {
        StreamsIOService ioService = new StreamsIOService();
        ioService.printLine("ok");
    }
}
