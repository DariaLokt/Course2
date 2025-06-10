package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.ExceedingAmountException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new ExceedingAmountException();
        } else {
            List<Question> examList = new ArrayList<>();
            int i = 0;
            while (i < amount) {
                Question newQuestion = questionService.getRandomQuestion();
                if (!examList.contains(newQuestion)) {
                    examList.add(newQuestion);
                    i++;
                }
            }
            return examList;
        }

    }
}
