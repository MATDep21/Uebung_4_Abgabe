package vorlesung_5.exceptions;

/**
 * Exception, die ausgelöst wird, wenn ein ungültiger Name übergeben wird.
 *
 * @author Yacine Si-Mohammed
 * @version 1.0
 */

public class InvalidNameException extends RuntimeException {

    /**
     * Erstellt eine neue InvalidNameException mit einer übergebenen Fehlermeldung.
     *
     * @param message die Beschreibung des Fehlers.
     */

    public InvalidNameException(String message){
        super(message);
    }
}
