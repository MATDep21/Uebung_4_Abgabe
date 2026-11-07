package vorlesung_5;

import vorlesung_5.exceptions.DuplicateIdException;
import vorlesung_5.exceptions.InvalidNameException;
import java.util.*;

public class HealthManagement {
    static Map<Integer, Patient> patientMap = new HashMap<>();
    static Set<Patient> patientSet = new HashSet<>();
    static Map<Integer, Doctor> doctorMap = new HashMap<>();
    static Set<Doctor> doctorSet = new HashSet<>();

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

    public static void addPerson(Person p)throws IllegalArgumentException {
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