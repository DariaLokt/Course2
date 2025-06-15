package pro.skyjava.course2.examinerservice.exception;

public class BlankQuestionException extends RuntimeException {
    public BlankQuestionException() {
        super("Пустой вопрос");
    }
}
