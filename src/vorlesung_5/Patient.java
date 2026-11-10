package vorlesung_5;

/**
 * Definiert einen Patienten, der von der abstrakten Klasse Person erbt.
 * Ein Patient besitzt einen Namen und eine eindeutige ID.
 *
 * @author Yacine Si-Mohammed
 * @author Mattis Pallokat
 * @version 1.0
 */

public class Patient extends Person {
    /**
     * Erstellt einen Patienten anhand des übergebenen Namens und ID
     * @param name Name des Patienten
     * @param id Eindeutige ID des Patienten
     */

    public Patient(String name, int id){
        super(name,id);
    }

    /**
     * Gibt den Namen und die ID des Patienten als String auf die Konsole aus.
     */

    @Override
    public void displayInfo(){
        System.out.println("Patient: Name: " + getName() + ", ID: " + getId());
    }

    /**
     * Gibt die ID des Patienten zurück
     * @return Die eindeutige ID des Patienten.
     */

    public int getPatientId(){
        return getId();
    }
}