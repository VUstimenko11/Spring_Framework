package org.example.service;

import org.example.domain.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;

    public StudentServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student askStudentCredentials() {
        String lastName = ioService.readStringWithPrompt("Please enter your last name:");
        String firstName = ioService.readStringWithPrompt("Please enter your first name:");
        return new Student(firstName, lastName);
    }
}
