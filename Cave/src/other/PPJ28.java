package other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PPJ28 {

    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\d{2,10}\\s");
        //Pattern p = Pattern.compile("l1+"); początek + reszta
        //Pattern p = Pattern.compile("l1*"); czy jest gdzieś l 0 lub więcej razy
        //Pattern p = Pattern.compile("l1?"); tekst jest opcjonalny
        //Pattern p = Pattern.compile("l1."); dowolny symbol
        //Pattern p = Pattern.compile("l1."); dowolny symbol
        //Pattern p = Pattern.compile("^[0-3]+.");  ^ negacja
        // \\d tylko cyfry \\D nie cyfry
        // \\w znaki[a-zA-Z0-9_] \\W zaprzeczenie \\s znaki białe (spacja itp)
        // \\d{2}-\\d{3} kod pocztowy \\d{4}-\\d{2}-\\d{2} data | lub
        String str = "123 llakjlkjcda 172938a lajdasl 01982338 $^$%^ akhdks";
        Matcher m = p.matcher(str);

        System.out.println(m.find());
        int start = 0;
        while (m.find(start) && start >= 0) {
            System.out.println(m.group());
            start = str.indexOf(m.group(), start);
            start += start >= 0 ? m.group().length() + 1 : 0;
        }
    }

    public static void cw1(){

    }
}
