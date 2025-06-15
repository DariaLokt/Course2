package pro.skyjava.course2.examinerservice.exception;

public class ExistingQuestionException extends RuntimeException {
    public ExistingQuestionException() {
        super("Уже есть такой вопрос");
    }

}
