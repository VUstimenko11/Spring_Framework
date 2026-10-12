package org.example.service;

import org.example.domain.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final LocalizedIOService ioService;

    public StudentServiceImpl(LocalizedIOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student askStudentCredentials() {
        String lastName = ioService.readStringLocalized("student.last-name");
        String firstName = ioService.readStringLocalized("student.first-name");
        return new Student(firstName, lastName);
    }
}
