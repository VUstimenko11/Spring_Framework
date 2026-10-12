package org.example.service;

import org.example.config.AppProperties;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

final class TestLocalizedIOFactory {

    private TestLocalizedIOFactory() {
    }

    static LocalizedIOService create(RecordingIOService ioService) {
        AppProperties properties = new AppProperties();
        properties.setLocale(Locale.ENGLISH);
        properties.getTest().setPassingScore(80);

        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("app.title", Locale.ENGLISH, "Student testing application");
        messageSource.addMessage("student.last-name", Locale.ENGLISH, "Please enter your last name:");
        messageSource.addMessage("student.first-name", Locale.ENGLISH, "Please enter your first name:");
        messageSource.addMessage("test.please-answer", Locale.ENGLISH, "Please answer the questions:");
        messageSource.addMessage("test.enter-answer", Locale.ENGLISH, "Enter the answer number:");
        messageSource.addMessage("test.invalid-answer", Locale.ENGLISH, "Invalid answer number. Please try again.");
        messageSource.addMessage("result.header", Locale.ENGLISH, "Test result:");
        messageSource.addMessage("result.student", Locale.ENGLISH, "Student: {0}");
        messageSource.addMessage("result.correct", Locale.ENGLISH, "Correct answers: {0} of {1}");
        messageSource.addMessage("result.required", Locale.ENGLISH, "Required to pass: {0} of {1} ({2}%)");
        messageSource.addMessage("result.status", Locale.ENGLISH, "Status: {0}");
        messageSource.addMessage("result.passed", Locale.ENGLISH, "PASSED");
        messageSource.addMessage("result.failed", Locale.ENGLISH, "FAILED");

        return new LocalizedIOService(ioService, messageSource, properties);
    }

    static AppProperties properties() {
        AppProperties properties = new AppProperties();
        properties.setLocale(Locale.ENGLISH);
        properties.getTest().setPassingScore(80);
        return properties;
    }
}
