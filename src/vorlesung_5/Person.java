package vorlesung_5;

import vorlesung_5.exceptions.InvalidNameException;

import java.util.Objects;

/**
 * Abstrakte Basisklasse für Personen mit einem Namen und einer eindeutigen ID.
 * Jede Person kann ihre Informationen anzeigen und anhand ihrer ID mit anderen Personen verglichen werden
 *
 * @author Yacine Si-Mohammed
 * @author Mattis Pallokat
 * @version 1.0
 */

public abstract class Person implements Comparable<Person>, Identifiable{

    private String name;
    private int id;

    /**
     * Erstellt eine Person anhand des übergebenen Namens und ID.
     *
     * @param name Name der Person
     * @param id einzigartige ID der Person
     * @throws InvalidNameException wird geworfen, wenn der Name {@code null} ist, leer ist oder
     * nur aus Leerzeichen besteht.
     */

    public Person(String name, int id) throws InvalidNameException {

        if(name == null || name.isBlank()){
            throw new InvalidNameException("Name darf weder leer sein noch ausschließlich aus Leerzeichen bestehen!");
        }
        else {
            this.name = name;
            this.id = id;
        }
    }

    /**
     * Gibt alle Informationen der Person auf der Konsole aus.
     */
    public abstract void displayInfo();

    /**
     * Gibt eindeutige ID der Person zurück.
     *
     * @return Eindeutige ID der Person
     */

    @Override
    public int getId() {
        return id;
    }

    /**
     * Gibt den Namen der Person zurück
     *
     * @return Namen der Person
     */

    @Override
    public String getName(){
        return name;
    }

     /**
     * Überprüft, ob zwei Personen gleich sind anhand der ID
     *
     * @param o die Referenzperson mit welcher verglichen wird.
     * @return {@code true}, wenn die zwei Personen gleich sind, sonst {@code false}
     */

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    /**
     *
     * Berechnet den Hashcode anhand der ID der Person.
     *
     * @return der Hashcode der Person
     */

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    /**
     * Vergleicht zwei Personen anhand ihrer eindeutigen ID
     *
     * @param p die Person mit der verglichen wird.
     * @return {@code -1} wenn die ID kleiner ist, {@code 0} wenn IDs gleich groß sind, {@code 1} wenn ID größer ist.
     */

    @Override
    public int compareTo(Person p){
        int val = 0;
        if(this.id < p.id){
            val = -1;
        }
        else if(this.id > p.id){
            val = 1;
        }
        else if(this.id == p.getId()){
            return 0;
        }
        return val;
    }
}
