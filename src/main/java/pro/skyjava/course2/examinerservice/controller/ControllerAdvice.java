package pro.skyjava.course2.examinerservice.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pro.skyjava.course2.examinerservice.exception.ExceedingAmountException;
import pro.skyjava.course2.examinerservice.exception.ExistingQuestionException;
import pro.skyjava.course2.examinerservice.exception.NoSuchQuestionException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ExistingQuestionException.class)
    public ResponseEntity<QuestionError> handleExistingQuestionException
            (ExistingQuestionException e) {
        QuestionError questionError = new QuestionError("ExistingQuestionCode", "Уже есть такой вопрос");
        return new ResponseEntity<QuestionError>(questionError, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(NoSuchQuestionException.class)
    public ResponseEntity<QuestionError> handleNoSuchQuestionException
            (NoSuchQuestionException e) {
        QuestionError questionError = new QuestionError("NoSuchQuestionCode", "Нет такого вопроса");
        return new ResponseEntity<QuestionError>(questionError, HttpStatusCode.valueOf(404));
    }

    @ExceptionHandler(ExceedingAmountException.class)
    public ResponseEntity<QuestionError> handleExceedingAmountException
            (ExceedingAmountException e) {
        QuestionError questionError = new QuestionError("ExceedingAmountCode", "Столько вопросов нет в базе");
        return new ResponseEntity<QuestionError>(questionError, HttpStatusCode.valueOf(404));
    }
}
