package vorlesung_5.exceptions;

/**
 * Exception, die ausgelöst wird, wenn eine ID auf mehrere Personen zugewiesen wird.
 *
 * @author Mattis Pallokat
 * @version 1.0
 */

public class DuplicateIdException extends RuntimeException {

    /**
     * Erstellt eine neue InvalidNameException mit einer übergebenen Fehlermeldung.
     *
     * @param message die Beschreibung des Fehlers
     */

    public DuplicateIdException(String message) {
        super(message);
    }
}
