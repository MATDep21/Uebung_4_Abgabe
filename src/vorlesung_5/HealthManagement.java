package vorlesung_5;

import vorlesung_5.exceptions.DuplicateIdException;
import vorlesung_5.exceptions.InvalidNameException;
import java.util.*;

/**
 * Verwaltet Doctor- und Patientenobjekte, und speichert diese in Sets und Maps. Ermöglicht das Suchen und Ausgeben
 * einzelner sowie aller gespeicherten Objekte.
 *
 * @author Mattis Pallokat
 * @author Yacine Si-Mohammed
 * @version 1.0
 */

public class HealthManagement {
    static Map<Integer, Patient> patientMap = new HashMap<>();
    static Set<Patient> patientSet = new HashSet<>();
    static Map<Integer, Doctor> doctorMap = new HashMap<>();
    static Set<Doctor> doctorSet = new HashSet<>();

    /**
     * Erstellt die Doctor- und Patientenobjekte und führt die addPerson, findPersonById und listPersonal Methoden aus.
     * Zudem wird in der Methode bei Erstellung der Objekte und der Ausführung der Methoden nach möglichen Fehlern und
     * Exceptions gesucht.
     */

    public static void main(String[] args) {
        Patient hugo = new Patient("Hugo", 1993);
        try {
            Patient theManWithNoName = new Patient(null, 1995);
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
            // richtige exception einfügen
        }
        Patient vince = new Patient("Vince", 1991);
        Patient said = new Patient("Said", 1992);
        Doctor zidane = new Doctor("Zidane", 2010);
        try {
            Doctor leer = new Doctor("   ", 2005);
        } catch (InvalidNameException e) {
            System.out.println(e.getMessage());
        }
        Doctor özil = new Doctor("Özil", 2014);
        Doctor maza = new Doctor("Maza", 2026);
        Doctor copyCat = new Doctor("Piko",1993);
        Patient zeroSum = null;
        Doctor doppelGänger = new Doctor("Zidane", 2010);

        addPerson(hugo);
        addPerson(vince);
        try {
            addPerson(zeroSum);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        addPerson(said);
        addPerson(zidane);
        addPerson(özil);
        try{
            addPerson(copyCat);
        }
        catch (DuplicateIdException e){
            System.out.println(e.getMessage());
        }
        addPerson(maza);
        //addPerson(doppelGänger);
        listPersonal();
        findPersonById(1993);
        try{
            findPersonById(9999);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
    Eine Checked Exception ist sinnvoller, um erwartbare Probleme bei korrektem Code abzufangen, wie zum Beispiel
    fehlende Namen oder doppelte IDs.
    Eine Unchecked Exception dient wiederum Fehler im Code, wie zum Beispiel IndexOutOfBoundsException, welche während
    der Entwicklung des Codes auf Programmierfehler aufzeigt.
    Defensives Programmieren ist das Vorbereiten eines Codes auf unerwartete Eingaben, Fehler oder ungültige Zustände.
    Die Vorbereitung wird mithilfe Exceptions umgesetzt, was schlussendlich dazu führt, dass Fehler präventiv behandelt
    werden und das Programm damit robuster und fehlertoleranter wird.
     */

    /**
     * Fügt das übergebene Objekt in die passende Map und Set ein. Prüft, ob das übergebene Objekt nicht leer ist, ob es
     * schon nicht schon eingefügt wurde, und ob die ID des Objektes noch nicht vergeben wurde.
     *
     * @param p Doctor- oder Patientenobjekte welches in die jeweilige Map und Set hinzugefügt wird.
     * @throws IllegalArgumentException wird geworfen, wenn das übergebene Objekt leer ist.
     * @throws DuplicateIdException wird geworfen, wenn das übergebene Objekt eine schon existierende ID hat.
     */

    public static void addPerson(Person p)throws IllegalArgumentException, DuplicateIdException {
        if(p == null){
            throw new IllegalArgumentException("Objekt ist Leer");
        }
        else {
            for(Patient p2 : patientSet){
                if(p.getId()==p2.getPatientId()){
                    throw new DuplicateIdException("Die ID "+p.getId()+" wird bereits verwendet.");
                }
            }
            for(Doctor p2 : doctorSet){
                if(p.getId()==p2.getDoctorId()){
                    throw new DuplicateIdException("Die ID "+p.getId()+" wird bereits verwendet.");
                }
            }
            if (p instanceof Patient) {
                if (!patientSet.contains(p)) {
                    patientSet.add((Patient) p);
                    patientMap.put(p.getId(), (Patient) p);
                } else {
                    System.out.println(p.getName() + " wurde schon eingetragen");
                }
            }
            if (p instanceof Doctor) {
                if (!doctorSet.contains(p)) {
                    doctorSet.add((Doctor) p);
                    doctorMap.put(p.getId(), (Doctor) p);
                } else {
                    System.out.println(p.getName() + " wurde schon eingetragen");
                }
            }
        }
    }

    /**
     * Sucht aus allen Maps und Sets das passende Doctor- oder Patientenobjekte raus, und nutzt dafür die ID des
     * übergebenen Objektes. Wenn das gesuchte Objekt gefunden wurde, werden alle Informationen ausgeben.
     *
     * @param id Eideutige ID eines Doctor- oder Patientenobjekt.
     * @throws NoSuchElementException wird geworfen, wenn die gesuchte Person nicht existiert.
     */

    public static void findPersonById(int id) throws NoSuchElementException {
        boolean exists = false;
        if (patientMap.containsKey(id)) {
            patientMap.get(id).displayInfo();
            exists = true;
        }
        if (doctorMap.containsKey(id)) {
            doctorMap.get(id).displayInfo();
            exists = true;
        }
        if(!exists){
            throw new NoSuchElementException("ID exsistiert weder unter Doctoren noch Patienten!");
        }
    }

    /**
     *Gibt alle informationen zu allen Doctor- und Patientenobjekte aus.
     *Sortierung wird mithilfe einer ArrayList ermöglicht.
     */

    public static void listPersonal(){
      List <Person> liste = new ArrayList<>();
      liste.addAll(patientSet);
      liste.addAll(doctorSet);
      Collections.sort(liste);
      for(Person p: liste){
          p.displayInfo();
      }
    }
}

/**
 *-InvalidNameException hat ursprünglich von Exception geerbt, dieser ist checked und somit auch InvalidNameException
 *-NoSuchElementException gehört zu den RuntimeExceptions und ist somit Unchecked
 *-Änderung der Exception ändert wie der Compiler mit einem Fehler umgeht,
 * da jetzt keine prüfung verlangt für die checked Exception verlangt wird sondern nun freiwilig ist.
 *
 */