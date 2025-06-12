package pro.skyjava.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.ExceedingAmountException;
import pro.skyjava.course2.examinerservice.exception.RandomizerErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

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
            long startTime = System.currentTimeMillis();
            while (i < amount) {
                if (System.currentTimeMillis() - startTime > 10000) {
                    throw new RandomizerErrorException();
                }
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
