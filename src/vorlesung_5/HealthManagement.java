package vorlesung_5;

import vorlesung_5.exceptions.DuplicateIdException;
import vorlesung_5.exceptions.InvalidNameException;
import java.util.*;

/**
 * Verwaltet Doctor- und Patientenobjekten, und speichert diese in Sets und Maps. Ermöglicht das Suchen und Ausgeben
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
     * Erstellt die Doctor- und Patientenobjekten, und testet die addPerson, findPersonById und listPersonal Methoden.
     * (kp ob testen richtig ist)
     */
    public static void main(String[] args) {
        Patient p1 = new Patient("Hugo", 1992);
        Patient p2 = new Patient("Vince", 1991);
        Patient p3 = new Patient("Said", 1992);
        Doctor d1 = new Doctor("Zidane", 2010);
        Doctor d2 = new Doctor("Özil", 2016);
        Doctor d3 = new Doctor("Maza", 2026);
        addPerson(p1);
        addPerson(p2);
        addPerson(p3);
        addPerson(p1);
        addPerson(d1);
        addPerson(d2);
        addPerson(d3);
        addPerson(d1);
        findPersonById(2026);
        findPersonById(2022);
        //listPersonal();
    }

    /**
     * Fügt das übergebene Objekt in die passende Map und Set ein. Prüft, ob das übergebene Objekt nicht leer ist, ob es
     * schon nicht schon eingefügt wurde, und ob die ID des Objektes noch nicht vergeben wurde.
     *
     * @param p Doctor- oder Patientenobjekten welches in die jeweilige Map und Set hinzugefügt wird.
     * @throws IllegalArgumentException wird geworfen, wenn das übergebene Objekt leer ist.
     * @throws DuplicateIdException wird geworfen, wenn das übergebene Objekt eine schon existierende ID hat.
     */
    public static void addPerson(Person p)throws IllegalArgumentException, DuplicateIdException {
        if(p == null){
            throw new IllegalArgumentException("Person ist Leer");
        }
        else {
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
    }

    /**
     * Sucht aus allen Maps und Sets das passende Doctor- oder Patientenobjekten raus, und nutzt dafür die ID des
     * übergebenen Objektes. Wenn das gesuchte Objekt gefunden wurde, werden alle Informationen ausgeben.
     *
     * @param id Eideutige ID eines Doctor- oder Patientenobjekt.
     * @throws InvalidNameException wird geworfen, wenn die gesuchte Person nicht existiert.
     */
    public static void findPersonById(int id) throws InvalidNameException {
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
     *Gibt alle informationen zu allen Doctor- und Patientenobjekten aus.
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