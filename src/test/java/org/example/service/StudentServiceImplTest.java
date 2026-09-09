package org.example.service;

import org.example.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceImplTest {

    @Test
    @DisplayName("Should ask last name and first name")
    void shouldAskStudentCredentials() {
        RecordingIOService recordingIOService = new RecordingIOService()
                .withStringAnswers("Ivanov", "Ivan");
        LocalizedIOService ioService = TestLocalizedIOFactory.create(recordingIOService);
        StudentService studentService = new StudentServiceImpl(ioService);

        Student student = studentService.askStudentCredentials();

        assertEquals("Ivan", student.getFirstName());
        assertEquals("Ivanov", student.getLastName());
        assertEquals("Ivan Ivanov", student.getFullName());
    }
}
