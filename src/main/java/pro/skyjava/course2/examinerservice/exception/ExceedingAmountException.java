package pro.skyjava.course2.examinerservice.exception;

public class ExceedingAmountException extends RuntimeException {
    public ExceedingAmountException() {
        super("Столько вопросов нет в базе");
    }

}
