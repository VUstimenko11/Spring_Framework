package org.example.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppPropertiesTest {

    @Test
    @DisplayName("Should resolve localized questions resource name")
    void shouldResolveQuestionsResourceName() {
        AppProperties properties = new AppProperties();
        properties.setLocale(new Locale("ru"));
        properties.getTest().setFileName("questions");
        properties.getTest().setPassingScore(80);

        assertEquals("questions_ru.csv", properties.resolveQuestionsResourceName());
        assertEquals(80, properties.getPassingScore());
    }
}
