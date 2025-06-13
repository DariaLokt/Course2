package pro.skyjava.course2.examinerservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.skyjava.course2.examinerservice.domain.Question;
import pro.skyjava.course2.examinerservice.exception.ExistingQuestionException;
import pro.skyjava.course2.examinerservice.exception.NoSuchQuestionException;
import pro.skyjava.course2.examinerservice.repository.QuestionRepository;

import java.util.Collections;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    @DisplayName("Выбрасывает исключение, если добавлять вопрос, который уже есть")
    void givenExistingQuestion_whenAdd_thenThrowsException() {
//        given
        Question trail = new Question("Sun", "Moon");

//        when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Set.of(trail));

//        then
        Assertions.assertThrows(ExistingQuestionException.class, () -> javaQuestionService.add(trail));
    }

    @Test
    @DisplayName("Добавляет новый вопрос")
    void givenNewQuestion_whenAdd_thenAdds() {
//        given
        Question trail = new Question("Sun", "Moon");

//        when
        javaQuestionService.add(trail);

//        then
        Mockito.verify(questionRepository).add(trail);
    }

    @Test
    @DisplayName("Выбрасывает исключение, если удалять вопрос, которого нет")
    void givenNoSuchQuestion_whenRemove_thenThrowsException() {
//        given
        Question trail = new Question("Sun", "Moon");
        Question extra = new Question("Cat", "Dog");

//        when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Set.of(extra));

//        then
        Assertions.assertThrows(NoSuchQuestionException.class, () -> javaQuestionService.remove(trail));
    }

    @Test
    @DisplayName("Удаляет вопрос")
    void givenExistingQuestion_whenRemove_thenRemoves() {
//        given
        Question trail = new Question("Sun", "Moon");
        Question trail2 = new Question("Earth", "Mars");

//        when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Set.of(trail,trail2));
        javaQuestionService.remove(trail);

//        then
        Mockito.verify(questionRepository).remove(trail);
    }

    @Test
    @DisplayName("Возвращает коллекцию вопросов из репозитория, когда они там есть")
    void givenRepositoryHasQuestions_whenGetAll_thenReturnsCollection() {
//        given
        Question trail = new Question("Sun", "Moon");
        Question trail2 = new Question("Earth", "Mars");
        Question trail3 = new Question("Venus", "Mercury");

//        when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Set.of(trail,trail2,trail3));
        javaQuestionService.getAll();

//        then
        Mockito.verify(questionRepository).getSetOfQuestions();
        Assertions.assertEquals(javaQuestionService.getAll(),questionRepository.getSetOfQuestions());
    }

    @Test
    @DisplayName("Возвращает пустой сет")
    void givenRepositoryHasNoQuestions_whenGetAll_thenReturnsBlank() {
//        given&when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Collections.emptySet());

//        then
        Assertions.assertEquals(javaQuestionService.getAll(),Collections.emptySet());
    }

    @Test
    @DisplayName("Возвращает один из рандомных вопросов репозитория")
    void givenRepositoryHasQuestions_whenGetRandom_thenGetsOneQuestion() {
//        given
        Question trail = new Question("Sun", "Moon");
        Question trail2 = new Question("Earth", "Mars");
        Question trail3 = new Question("Venus", "Mercury");

//        when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Set.of(trail,trail2,trail3));

//        then
        Assertions.assertTrue(questionRepository.getSetOfQuestions().contains(javaQuestionService.getRandomQuestion()));
    }

    @Test
    @DisplayName("Возвращает вопросы, которые содержат запрос")
    void givenDesiredQuestionExists_whenFind_thenReturnsSetOfStringQuestions() {
//        given
        Question trail = new Question("Sun", "Moon");
        Question trail2 = new Question("Earth", "Mars");
        Question trail3 = new Question("Venus", "Mercury");

//        when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Set.of(trail,trail2,trail3));

//        then
        Assertions.assertNotEquals(Collections.<String>emptySet(),javaQuestionService.findQuestionBySearchTerm("u"));
    }

    @Test
    @DisplayName("Возвращает пустой сет, когда нет вопросов с искомым запросом")
    void givenNoDesiredQuestion_whenFind_thenReturnsEmptySet() {
//        given
        Question trail = new Question("Sun", "Moon");
        Question trail2 = new Question("Earth", "Mars");
        Question trail3 = new Question("Venus", "Mercury");

//        when
        Mockito.when(questionRepository.getSetOfQuestions()).thenReturn(Set.of(trail,trail2,trail3));

//        then
        Assertions.assertEquals(Collections.<String>emptySet(),javaQuestionService.findQuestionBySearchTerm("1"));
    }
}