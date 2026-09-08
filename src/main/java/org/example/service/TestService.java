package org.example.service;

import org.example.domain.Student;
import org.example.domain.TestResult;

public interface TestService {

    TestResult executeTestFor(Student student);
}
