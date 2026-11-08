package vorlesung_5;

/**
 *Interface zur Identifizierung von Objekten anhand einer ID und eines Namens.
 *
 *@author Yacine Si-Mohammed
 *@version 1.0
 */
public interface Identifiable {
    /**
     *Gibt die eindeutige ID des Objekts zurück.
     * @return die ID des Objekts
     */
    public int getId();

    /**
     *Gibt den Namen des Objekts zurück.
     * @return den Namen des Objekts
     */
    public String getName();
}
