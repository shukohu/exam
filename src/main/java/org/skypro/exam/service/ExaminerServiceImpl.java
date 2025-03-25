package org.skypro.exam.service;

import org.skypro.exam.Question;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final JavaQuestionService questionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(JavaQuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> availableQuestions = new ArrayList<>(questionService.getAll());
        if (amount > availableQuestions.size()) {
            throw new IllegalArgumentException("Запрошено больше вопросов, чем доступно.");
        }
        Set<Question> selectedQuestions = new HashSet<>();
        while (selectedQuestions.size() < amount) {
            selectedQuestions.add(availableQuestions.get(random.nextInt(availableQuestions.size())));
        }
        return selectedQuestions;
    }
}
