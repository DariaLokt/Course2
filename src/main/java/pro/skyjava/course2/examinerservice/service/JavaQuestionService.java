package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.ExistingQuestionException;
import pro.skyjava.course2.examinerservice.exception.NoSuchQuestionException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService{
    Set<Question> questions;
    int counter;

    public JavaQuestionService(Set<Question> questions) {
        this.questions = questions;
        counter = questions.size();
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question,answer);
        if (questions.contains(newQuestion)) {
            throw new ExistingQuestionException();
        } else {
            questions.add(newQuestion);
            counter++;
            System.out.println("Вопрос добавлен");
        }
        return newQuestion;
    }

    @Override
    public Question add(Question newQuestion) {
        if (questions.contains(newQuestion)) {
            throw new ExistingQuestionException();
        } else {
            questions.add(newQuestion);
            counter++;
            System.out.println("Вопрос добавлен");
        }
        return newQuestion;
    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            counter--;
            System.out.println("Вопрос удалён");
        } else {
            throw new NoSuchQuestionException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int questionNumber = random.nextInt(0, questions.size());
        Question[] questionsArray = questions.toArray(new Question[0]);
        return questionsArray[questionNumber];
    }

    @Override
    public Set<String> findQuestionBySearchTerm(String searchTerm) {
        return questions.stream()
                .map(Question::getQuestion)
                .filter(q -> q.contains(searchTerm))
                .collect(Collectors.toSet());
    }
}
