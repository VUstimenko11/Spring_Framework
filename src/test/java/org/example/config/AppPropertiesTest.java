package org.example.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppPropertiesTest {

    @Test
    @DisplayName("Should store file name and passing score")
    void shouldStoreProperties() {
        AppProperties properties = new AppProperties("questions.csv", 80);

        assertEquals("questions.csv", properties.getTestFileName());
        assertEquals(80, properties.getPassingScore());
    }
}
