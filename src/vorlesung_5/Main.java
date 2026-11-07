package vorlesung_5;

import java.util.*;

public class Main {
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
        findPerson(2026);
        findPerson(2022);
        //listPersonal();
    }

    public static void addPerson(Person p) {
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

    public static void findPerson(int id) {
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
            System.out.println("ID exsistiert weder unter Doctoren noch Patienten!");
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