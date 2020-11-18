package other.GUI3;

public class Student implements Comparable<Student> {
    public String imie;
    public int indeks;
    private static int counter;

    public Student(String imie) {
        this.imie = imie;
        indeks = counter++;
    }

    @Override
    public int compareTo(Student o) {
        return this.indeks - o.indeks;
    }
}
