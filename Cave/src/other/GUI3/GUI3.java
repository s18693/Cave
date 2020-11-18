package other.GUI3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GUI3 {

    public static void main(String[] args) {
        //less();

    }

    static void less() {
        List<Student> list = new ArrayList<Student>();

        list.add(new Student("Adam"));
        list.add(new Student("Ewa"));
        list.add(new Student("Jan"));

        Collections.sort(list);

        for (Student student : list)
            System.out.println(student.indeks + " " + student.imie);
    }
}
