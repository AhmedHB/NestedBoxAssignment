package exception;

public class ColoredBoxNotFoundException extends Exception {
    public ColoredBoxNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}