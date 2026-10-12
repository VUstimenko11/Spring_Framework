package org.example.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class RecordingIOService implements IOService {

    private final Queue<String> stringAnswers = new ArrayDeque<>();
    private final Queue<Integer> intAnswers = new ArrayDeque<>();
    private final List<String> printedLines = new ArrayList<>();

    RecordingIOService withStringAnswers(String... answers) {
        stringAnswers.clear();
        for (String answer : answers) {
            stringAnswers.add(answer);
        }
        return this;
    }

    RecordingIOService withIntAnswers(Integer... answers) {
        intAnswers.clear();
        for (Integer answer : answers) {
            intAnswers.add(answer);
        }
        return this;
    }

    List<String> getPrintedLines() {
        return List.copyOf(printedLines);
    }

    @Override
    public void printLine(String line) {
        printedLines.add(line);
    }

    @Override
    public void printFormattedLine(String format, Object... args) {
        printedLines.add(String.format(format, args));
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        printedLines.add(prompt);
        if (stringAnswers.isEmpty()) {
            throw new IllegalStateException("No string answers prepared for prompt: " + prompt);
        }
        return stringAnswers.remove();
    }

    @Override
    public int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage) {
        printedLines.add(prompt);
        if (intAnswers.isEmpty()) {
            throw new IllegalStateException("No int answers prepared for prompt: " + prompt);
        }
        return intAnswers.remove();
    }
}
