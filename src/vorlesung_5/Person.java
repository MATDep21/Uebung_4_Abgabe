package vorlesung_5;

import vorlesung_5.exceptions.InvalidNameException;

import java.util.Objects;

public abstract class Person implements Comparable<Person>, Identifiable{

    private String name;
    private int id;

    public Person(String name, int id) throws InvalidNameException {

        if(name == null || name.isBlank()){
            throw new InvalidNameException("Name ist ungültig!");
        }
        else {
            this.name = name;
            this.id = id;
        }
    }

    public abstract void displayInfo();

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Person p){
        int val = 0;
        if(this.id < p.id){
            val = -1;
        }
        else if(this.id > p.id){
            val = 1;
        }
        else {
            val = 0;
        }
        return val;
    }

}
