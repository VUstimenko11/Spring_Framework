package org.example.dao;

import org.example.config.AppProperties;
import org.example.domain.Answer;
import org.example.domain.Question;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CsvQuestionDao implements QuestionDao {

    private final String resourceName;

    public CsvQuestionDao(AppProperties appProperties) {
        this.resourceName = appProperties.getTestFileName();
    }

    @Override
    public List<Question> findAll() {
        try (InputStream inputStream = new ClassPathResource(resourceName).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(this::parseQuestion)
                    .toList();
        } catch (IOException e) {
            throw new QuestionReadException("Failed to read questions from resource: " + resourceName, e);
        }
    }

    private Question parseQuestion(String line) {
        String[] parts = Arrays.stream(line.split(";"))
                .map(String::trim)
                .filter(part -> !part.isEmpty())
                .toArray(String[]::new);

        if (parts.length < 2) {
            throw new QuestionReadException("Invalid CSV line (expected question and answers): " + line);
        }

        String questionText = parts[0];
        List<Answer> answers = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            answers.add(parseAnswer(parts[i]));
        }
        return new Question(questionText, answers);
    }

    private Answer parseAnswer(String rawAnswer) {
        String[] answerParts = rawAnswer.split("\\|");
        String answerText = answerParts[0].trim();
        boolean correct = answerParts.length > 1 && Boolean.parseBoolean(answerParts[1].trim());
        return new Answer(answerText, correct);
    }
}
