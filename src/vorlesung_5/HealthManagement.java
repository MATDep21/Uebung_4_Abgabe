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
    private static Map<Integer, Patient> patientMap = new HashMap<>();
    private static Set<Patient> patientSet = new HashSet<>();
    private static Map<Integer, Doctor> doctorMap = new HashMap<>();
    private static Set<Doctor> doctorSet = new HashSet<>();

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
        findPersonById(1993);
        try{
            findPersonById(9999);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        listPersonal();

        /*
         * Aufgabe 3:
         *-InvalidNameException hat ursprünglich von Exception geerbt, Exception ist checked und somit auch InvalidNameException.
         * Jedoch soll im Anschluss laut Aufgabenstellung von RuntimeException geerbt werden, was es zu einer Unchecked Exception macht.
         *
         *-NoSuchElementException gehört zu den RuntimeExceptions und ist somit Unchecked
         *
         *-Änderung der Exception, ändert wie der Compiler mit einem Fehler umgeht,
         * da jetzt keine prüfung für die checked Exception verlangt wird sondern nun freiwillig ist.
         *
         *
         * Aufgabe 6:
         * -Eine Checked Exception ist sinnvoller, um erwartbare Fehler, die nicht vom Programm ausgeschlossen werden können,
         *  abzufangen. Der Compiler zwingt den Aufrufer dabei, den Fehler zu behandeln oder weiterzugeben.
         *  Beispielsweise FileNotFoundException oder I/O-Exception.
         *
         * -Eine Unchecked Exception dient wiederum dazu, Fehler im Code aufzuzeigen die zur Laufzeit auftritt und auf
         *  Programmierfehler hinweist. Sie wird nicht vom Compiler geprüft. Dementsprechend wären diese für vermeidbare Probleme
         *  wie fehlende Objekte/Attribute oder Zugriffe auf nicht Existierenden Array-Index von Vorteil.
         *
         * -Defensives Programmieren bedeutet, einen Code auf unerwartete Eingaben, Fehler oder ungültige Zustände vorzubereiten.
         *  Die Vorbereitung kann mithilfe von Exceptions umgesetzt werden, was schlussendlich dazu führt, dass Fehler frühzeitig
         *  erkannt und kontrolliert behandelt werden und das Programm dadurch robuster und fehlertoleranter wird.
         */
    }

    /**
     * Fügt das übergebene Objekt in die passende Map und Set ein. Prüft, ob das übergebene Objekt nicht leer ist, ob es
     * schon eingefügt wurde, und ob die ID des Objektes noch nicht vergeben wurde.
     *
     * @param p Doctor- oder Patientenobjekte welches in die jeweilige Map und Set hinzugefügt wird.
     * @throws IllegalArgumentException wird geworfen, wenn das übergebene Objekt {@code null} ist.
     * @throws DuplicateIdException wird geworfen, wenn das übergebene Objekt eine schon existierende ID hat.
     */

    public static void addPerson(Person p)throws IllegalArgumentException, DuplicateIdException {
        if(p == null){
            throw new IllegalArgumentException("Objekt darf nicht Null sein!");
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
                if (!patientSet.contains(p)) { // Redundant aber wegen vollständigkeit halber zu Hausaufgabe 3 trotzdem vorhanden
                    patientSet.add((Patient) p);
                    patientMap.put(p.getId(), (Patient) p);
                } else { // Redundant aber wegen vollständigkeit halber zu Hausaufgabe 3 trotzdem vorhanden
                    System.out.println(p.getName() + " wurde schon eingetragen");
                }
            }
            if (p instanceof Doctor) {
                if (!doctorSet.contains(p)) { // Redundant aber wegen vollständigkeit halber zu Hausaufgabe 3 trotzdem vorhanden
                    doctorSet.add((Doctor) p);
                    doctorMap.put(p.getId(), (Doctor) p);
                } else { // Redundant aber wegen vollständigkeit halber zu Hausaufgabe 3 trotzdem vorhanden
                    System.out.println(p.getName() + " wurde schon eingetragen");
                }
            }
        }
    }

    /**
     * Sucht aus allen Maps das passende Doctor- oder Patientenobjekte raus, und nutzt dafür die ID.
     * Wenn das gesuchte Objekt gefunden wurde, werden alle Informationen ausgegeben.
     *
     * @param id ID eines möglichen Doctor- oder Patientenobjekt.
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
            throw new NoSuchElementException("Keine Person mit der ID " + id + " gefunden");
        }
    }

    /**
     * Gibt Informationen zu allen Doctor- und Patientenobjekte aus.
     * Sortierung wird mithilfe einer ArrayList ermöglicht.
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