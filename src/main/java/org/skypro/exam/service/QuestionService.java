package org.skypro.exam.service;

import org.skypro.exam.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question remove(String question, String answer);

    Collection <Question> getAll();

    Question getRandomQuestion();
}
