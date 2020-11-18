package other.GUI1;

public class Person {
    protected String name, surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void sayHelloTo(Person p){
        System.out.println("Hi " + p.name);
    }

    public String toString(){
        return "Name: " + name + " Surname: " + surname;
    }
}
