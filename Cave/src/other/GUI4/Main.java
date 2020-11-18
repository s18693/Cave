package other.GUI4;

import other.GUI1.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static String name = "";
    static int max = 0;

    public static void main(String[] args) {
        //interface funkcyjny moze zabierac jedna niezdefinowana metoda
    /*
    Testowy t = new Testowy() {
        @Override
        public void show() {
            System.out.println("hello World");
        }
    }
     //lambda wyrazenie

        Testowy t = (name) -> "hello " + name;
        System.out.println(t.show("adam"));

*/
        int persons = 5, numberOfOfice = 0;
        String[] nameOfofice = new String[persons];

        Map<String, List<Person>> listMap = new HashMap<>();
        Map<String, Person> oficeWorker = new HashMap<>();

        ArrayList<Person> people = new ArrayList<>();

        String[] arr = {"office A", "John", "Doe",
                "office B", "John", "Brown",
                "office C", "Mary", "Jones",
                "office B", "Adam", "Rust",
                "office C", "Cindy", "Frost",
                "office A", "Kate", "Coe",
                "office A", "Bill", "Brown"};

        for (int a = 0; a < arr.length; a += 3) {
            if (listMap.containsKey(arr[a]))
                listMap.get(arr[a]).add(new Person(arr[a + 1], arr[a + 2]));
            else {
                List<Person> p = new ArrayList<Person>();
                p.add(new Person(arr[a + 1], arr[a + 2]));
                listMap.put(arr[a], p);
            }
        }
        System.out.println(listMap);


        /*
        for(String string : listMap.keySet()){
            List<Person> tmp = listMap.get(string);
            if(tmp.size() > max){
                max = tmp.size();
                name = string;
            }
        }
        */
        for (Map.Entry<String, List<Person>> entry : listMap.entrySet()) {
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
                name = entry.getKey();
            }
        }

        listMap.forEach((k, v) -> {
            if (v.size() > max) {
                max = v.size();
                name = k;
            }
        });
        System.out.println(name + " : " + max + " users ");
    }
}
