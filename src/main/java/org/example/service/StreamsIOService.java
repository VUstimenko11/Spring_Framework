package org.example.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class StreamsIOService implements IOService {

    private static final int MAX_ATTEMPTS = 10;

    private final PrintStream printStream;
    private final Scanner scanner;

    public StreamsIOService() {
        this(System.out, System.in);
    }

    StreamsIOService(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public void printLine(String line) {
        printStream.println(line);
    }

    @Override
    public void printFormattedLine(String format, Object... args) {
        printStream.printf(format + "%n", args);
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        printLine(prompt);
        return scanner.nextLine().trim();
    }

    @Override
    public int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage) {
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            printLine(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // fall through to error message
            }
            printLine(errorMessage);
        }
        throw new IllegalArgumentException("Failed to read a valid number after " + MAX_ATTEMPTS + " attempts");
    }
}
