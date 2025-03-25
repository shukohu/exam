package org.skypro.exam.service;

import org.skypro.exam.Question;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        if (!questions.add(q)) {
            throw new IllegalArgumentException("Такой вопрос уже существует!");
        }
        return q;
    }

    @Override
    public Question remove(String question, String answer) {
        Question q = new Question(question, answer);
        if (!questions.remove(q)) {
            throw new NoSuchElementException("Вопрос не найден.");
        }
        return q;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) throw new NoSuchElementException("Нет доступных вопросов.");
        int index = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(index);
    }
}
