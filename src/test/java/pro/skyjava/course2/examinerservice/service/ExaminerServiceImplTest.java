package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.ExceedingAmountException;
import pro.skyjava.course2.examinerservice.exception.RandomizerErrorException;
import pro.skyjava.course2.examinerservice.repository.QuestionRepository;

import java.util.Random;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionService questionService = new JavaQuestionService(questionRepository);
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    @DisplayName("Выбрасывает исключение, если вопросов меньше, чем в репозитории")
    void givenRepositoryHasLessQuestions_whenGetQuestions_thenThrowsException() {
        Question trail = new Question("Sun", "Moon");
        Mockito.when(questionService.getAll()).thenReturn(Set.of(trail));
        Assertions.assertThrows(ExceedingAmountException.class, () -> examinerService.getQuestions(questionService.getAll().size()+1));
    }

    @Test
    @DisplayName("Вообще возвращает вопросы")
    void givenRepositoryHasThisAmountOfQuestions_whenGetQuestions_thenGetsQuestions() {
        Question trail = new Question("Sun", "Moon");
        Question trail2 = new Question("Earth", "Mars");
        Question trail3 = new Question("Venus", "Mercury");
        Mockito.when(questionService.getAll()).thenReturn(Set.of(trail,trail2,trail3));
        Mockito.when(questionService.getRandomQuestion()).thenReturn(trail);
        examinerService.getQuestions(1);
        Mockito.verify(questionService).getRandomQuestion();
    }

    @Test
    @DisplayName("Не возвращает повторяющиеся вопросы")
    void givenRandomGivesSameQuestion_whenGetQuestions_thenThrowsException() {
        Question trail = new Question("Sun", "Moon");
        Question trail2 = new Question("Earth", "Mars");
        Question trail3 = new Question("Venus", "Mercury");
        Mockito.when(questionService.getAll()).thenReturn(Set.of(trail,trail2,trail3));
        Mockito.when(questionService.getRandomQuestion()).thenReturn(trail);
        Assertions.assertThrows(RandomizerErrorException.class, () -> examinerService.getQuestions(2));
    }
}