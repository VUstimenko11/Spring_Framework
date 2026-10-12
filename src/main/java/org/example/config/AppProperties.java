package org.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProperties {

    private Locale locale = Locale.ENGLISH;
    private TestProps test = new TestProps();

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public TestProps getTest() {
        return test;
    }

    public void setTest(TestProps test) {
        this.test = test;
    }

    public int getPassingScore() {
        return test.getPassingScore();
    }

    public String resolveQuestionsResourceName() {
        return test.getFileName() + "_" + locale.getLanguage() + ".csv";
    }

    public static class TestProps {

        private String fileName = "questions";
        private int passingScore = 80;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public int getPassingScore() {
            return passingScore;
        }

        public void setPassingScore(int passingScore) {
            this.passingScore = passingScore;
        }
    }
}
