package pro.skyjava.course2.examinerservice.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.BlankQuestionException;

import java.util.HashSet;
import java.util.Set;

class QuestionRepositoryTest {

    public Set<Question> setFillData() {
        Question fillData = new Question("Cat","Dog");
        Set<Question> fillSet = new HashSet<>();
        fillSet.add(fillData);
        return fillSet;
    }

    private final QuestionRepository questionRepository = new QuestionRepository(setFillData());

    @Test
    @DisplayName("Добавляет непустой вопрос")
    void givenFineQuestion_whenAdd_thenAdds() {
        Question question = new Question("Sun","Moon");
        Question fillData = new Question("Cat","Dog");
        questionRepository.add(question);
        Assertions.assertEquals(Set.of(question,fillData),questionRepository.getSetOfQuestions());
    }

    @Test
    @DisplayName("Выбрасывает исключение, когда вопрос пустой или нулл")
    void givenBlankQuestion_whenAdd_thenThrowsException() {
        Question blankQuestion = new Question("","");
        Question longerBlankQuestion = new Question("   ", "   ");
        Question nullQuestion = new Question(null,null);

        Assertions.assertThrows(BlankQuestionException.class, () -> questionRepository.add(blankQuestion));
        Assertions.assertThrows(BlankQuestionException.class, () -> questionRepository.add(longerBlankQuestion));
        Assertions.assertThrows(BlankQuestionException.class, () -> questionRepository.add(nullQuestion));
    }
}