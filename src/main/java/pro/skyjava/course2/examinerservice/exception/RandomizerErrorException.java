package pro.skyjava.course2.examinerservice.exception;

public class RandomizerErrorException extends RuntimeException {
    public RandomizerErrorException() {
        super("Рандомайзер вопросов сломался");
    }
}
