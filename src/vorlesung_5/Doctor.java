package vorlesung_5;

public class Doctor extends Person {

    public Doctor(String name, int id){
     super(name,id);
    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor: Name: " + getName() + ", ID: " + getId());
    }

    public int getDoctorId(){
        return getId();
    }
}
