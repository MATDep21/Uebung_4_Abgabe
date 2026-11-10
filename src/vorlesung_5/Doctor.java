package vorlesung_5;

import vorlesung_5.exceptions.InvalidNameException;

/**
 * Definiert einen Arzt, der von der abstrakten Klasse Person erbt.
 * Ein Doctor besitzt einen Namen und eine eindeutige ID.
 *
 * @author Mattis Pallokat
 * @author Yacine Si-Mohammed
 * @version 1.0
 */

public class Doctor extends Person {

    /**
     * Erstellt einen Doctor anhand des übergebenen Namens und ID.
     *
     * @param name Name des Doctors
     * @param id Eindeutige ID des Doctors
     * @throws InvalidNameException wird geworfen, wenn der Name {@code null}, leer ist oder
     * nur aus Leerzeichen besteht. Prüfung erfolgt in der Elternklasse Person.
     */

    public Doctor(String name, int id){
     super(name,id);
    }

    /**
     * Gibt den Namen und die ID des Doctors als String auf der Konsole aus.
     */

    @Override
    public void displayInfo() {
        System.out.println("Doctor: Name: " + getName() + ", ID: " + getId());
    }

    /**
     * Gibt die ID des Doctors zurück
     * @return Die eindeutige ID des Doctors.
     */

    public int getDoctorId(){
        return getId();
    }
}
