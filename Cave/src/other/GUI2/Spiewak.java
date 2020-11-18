package other.GUI2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Spiewak {
    String nazwisko;
    static int number;

    abstract String spiewaj();

    public Spiewak(String nazwisko) {
        this.nazwisko = nazwisko;
        this.number = Main.number;
        Main.number++;
    }

    public String toString() {
        return "number: " + number + " nazwisko: " + nazwisko;
    }

    public static Spiewak najglosniej(Spiewak[] spiewak) {
        int index = 0, Capital = 0, helper = 0;
        Pattern duze = Pattern.compile("[A-Z]");
        for (int a = 0; a < spiewak.length; a++) {
            Matcher good = duze.matcher(spiewak[a].spiewaj());
            int start = 0;
            while (good.find(start) && start >= 0) {
                helper++;
                start = spiewak[a].spiewaj().indexOf(good.group(), start);
                start += start >= 0 ? good.group().length() + 1 : 0;
            }
            if(helper > Capital){
                index = a;
                Capital = helper;
            }
            helper = 0;
        }
        return spiewak[index];
    }
}
