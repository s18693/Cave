package other.GUI2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

   public static int number = 0;

    public static void main(String[] args) {
        Spiewak s1 = new Spiewak("Kowalski") {
            @Override
            String spiewaj() {
                return "Trololololololol HAHAHA";
            }
        };
        Spiewak s2 = new Spiewak("Gruby") {
            @Override
            String spiewaj() {
                return "Dom i domek i Dom i domek";
            }
        };
        Spiewak[] listS = new Spiewak[2];
        listS[0] = s1;
        listS[1] = s2;

        Spiewak glosny = Spiewak.najglosniej(listS);
        System.out.println(glosny.nazwisko);

    }
}
