package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

    private final String testFileName;
    private final int passingScore;

    public AppProperties(@Value("${application.test.file-name}") String testFileName,
                         @Value("${application.test.passing-score}") int passingScore) {
        this.testFileName = testFileName;
        this.passingScore = passingScore;
    }

    public String getTestFileName() {
        return testFileName;
    }

    public int getPassingScore() {
        return passingScore;
    }
}
