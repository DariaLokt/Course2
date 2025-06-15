package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.ExistingQuestionException;
import pro.skyjava.course2.examinerservice.exception.NoSuchQuestionException;
import pro.skyjava.course2.examinerservice.repository.QuestionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService{
    private final QuestionRepository questionRepository;

    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question,answer);
        if (questionRepository.getSetOfQuestions().contains(newQuestion)) {
            throw new ExistingQuestionException();
        } else {
            questionRepository.add(newQuestion);
            System.out.println("Вопрос добавлен");
        }
        return newQuestion;
    }

    @Override
    public Question add(Question newQuestion) {
        if (questionRepository.getSetOfQuestions().contains(newQuestion)) {
            throw new ExistingQuestionException();
        } else {
            questionRepository.add(newQuestion);
            System.out.println("Вопрос добавлен");
        }
        return newQuestion;
    }

    @Override
    public Question remove(Question question) {
        if (questionRepository.getSetOfQuestions().contains(question)) {
            questionRepository.remove(question);
            System.out.println("Вопрос удалён");
        } else {
            throw new NoSuchQuestionException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getSetOfQuestions();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int questionNumber = random.nextInt(0, questionRepository.getSetOfQuestions().size());
        Question[] questionsArray = questionRepository.getSetOfQuestions().toArray(new Question[0]);
        return questionsArray[questionNumber];
    }

    @Override
    public Set<String> findQuestionBySearchTerm(String searchTerm) {
        return questionRepository.getSetOfQuestions().stream()
                .map(Question::getQuestion)
                .filter(q -> q.contains(searchTerm))
                .collect(Collectors.toSet());
    }
}
