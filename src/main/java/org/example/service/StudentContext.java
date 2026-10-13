package org.example.service;

import org.example.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentContext {

    private Student student;

    public void login(String firstName, String lastName) {
        this.student = new Student(firstName, lastName);
    }

    public Student getStudent() {
        return student;
    }

    public boolean isLoggedIn() {
        return student != null;
    }

    public void clear() {
        this.student = null;
    }
}
