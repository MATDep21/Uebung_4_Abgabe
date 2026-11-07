package vorlesung_5;

public class Patient extends Person {

    public Patient(String name, int id){
        super(name,id);
    }

    @Override
    public void displayInfo(){
        System.out.println("Patient: Name: " + getName() + ", ID: " + getId());
    }

    public int getPatientId(){
        return getId();
    }
}
