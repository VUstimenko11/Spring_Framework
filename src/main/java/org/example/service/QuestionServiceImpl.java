package org.example.service;

import org.example.dao.QuestionDao;
import org.example.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> findAll() {
        return questionDao.findAll();
    }
}
