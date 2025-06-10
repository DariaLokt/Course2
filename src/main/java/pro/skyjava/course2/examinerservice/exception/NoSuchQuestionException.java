package pro.skyjava.course2.examinerservice.exception;

public class NoSuchQuestionException extends RuntimeException {
    public NoSuchQuestionException() {
        super("Такого вопроса нет");
    }
}
