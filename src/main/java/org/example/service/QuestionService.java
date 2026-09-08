package org.example.service;

import org.example.domain.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();
}
