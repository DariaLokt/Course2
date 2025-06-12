package pro.skyjava.course2.examinerservice.repository;

import org.springframework.stereotype.Repository;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.BlankQuestionException;

import java.util.Set;

@Repository
public class QuestionRepository {
    Set<Question> questions;

    public QuestionRepository(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Question> getSetOfQuestions() {
        return questions;
    }

    public void add(Question question) {
        if (question.getQuestion() == null || question.getQuestion().isBlank()
        || question.getAnswer() == null || question.getAnswer().isBlank()) {
            throw new BlankQuestionException();
        } else {
            questions.add(question);
        }
    }

    public void remove(Question question) {
        questions.remove(question);
    }
}
