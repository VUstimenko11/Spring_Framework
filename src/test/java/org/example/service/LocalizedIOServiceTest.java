package org.example.service;

import org.example.config.AppProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizedIOServiceTest {

    @Test
    @DisplayName("Should resolve message through MessageSource")
    void shouldResolveLocalizedMessage() {
        RecordingIOService recordingIOService = new RecordingIOService().withStringAnswers("Ivanov");
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("student.last-name", Locale.ENGLISH, "Please enter your last name:");

        AppProperties properties = new AppProperties();
        properties.setLocale(Locale.ENGLISH);

        LocalizedIOService ioService = new LocalizedIOService(recordingIOService, messageSource, properties);

        String value = ioService.readStringLocalized("student.last-name");

        assertEquals("Ivanov", value);
        assertEquals("Please enter your last name:", recordingIOService.getPrintedLines().get(0));
    }
}
