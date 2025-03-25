package org.skypro.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.exam.service.JavaQuestionService;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void testAddQuestion() {
        Question question = questionService.add("Какой сок собирается весной?", "Березовый");
        assertThat(questionService.getAll()).contains(question);
    }

    @Test
    void testRemoveExistingQuestion() {
        Question question = questionService.add("Что согласно пословице весенний день кормит?", "Год");
        questionService.remove("Что согласно пословице весенний день кормит?", "Год");
        assertThat(questionService.getAll()).doesNotContain(question);
    }

    @Test
    void testRemoveNonExistentQuestion() {
        assertThrows(NoSuchElementException.class, () ->
                questionService.remove("Ошибка", "Недоступно"));
    }

    @Test
    void testGetAllQuestions() {
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");
        Set<Question> questions = Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        );

        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    void testGetRandomQuestion() {
        questionService.add("Q1", "A1");
        questionService.add("Q2", "A2");

        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    @Test
    void testGetRandomQuestionWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> questionService.getRandomQuestion());
    }
}
