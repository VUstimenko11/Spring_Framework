package org.example.service;

public interface IOService {

    void printLine(String line);

    void printFormattedLine(String format, Object... args);

    String readStringWithPrompt(String prompt);

    int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage);
}
