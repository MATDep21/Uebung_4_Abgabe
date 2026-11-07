package vorlesung_5.exceptions;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(String message) {
        super(message);
    }
}
