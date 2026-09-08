package org.example.service;

public class StreamsIOService implements IOService {

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    @Override
    public void printFormattedLine(String format, Object... args) {
        System.out.printf(format + "%n", args);
    }
}
