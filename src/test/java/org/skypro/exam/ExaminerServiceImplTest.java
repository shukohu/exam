package org.skypro.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.exam.service.ExaminerServiceImpl;
import org.skypro.exam.service.JavaQuestionService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {
    private JavaQuestionService questionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        questionService = mock(JavaQuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void testGetQuestionsValidAmount() {
        List<Question> questionList = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );

        when(questionService.getAll()).thenReturn(questionList);
        when(questionService.getRandomQuestion())
                .thenReturn(questionList.get(0), questionList.get(1));

        Set<Question> questions = (Set<Question>) examinerService.getQuestions(2);
        assertThat(questions).hasSize(2).containsAnyElementsOf(questionList);
    }

    @Test
    void testGetQuestionsMoreThanAvailable() {
        List<Question> questionList = List.of(new Question("Q1", "A1"));

        when(questionService.getAll()).thenReturn(questionList);

        assertThrows(IllegalArgumentException.class, () -> examinerService.getQuestions(2));
    }

    @Test
    void testGetQuestionsUnique() {
        List<Question> questionList = List.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        );

        when(questionService.getAll()).thenReturn(questionList);
        when(questionService.getRandomQuestion())
                .thenReturn(questionList.get(0), questionList.get(1), questionList.get(2));

        Collection<Question> questions = examinerService.getQuestions(3);
        assertThat(questions).hasSize(3).containsExactlyInAnyOrderElementsOf(questionList);
    }
}
