package other.PPJ29;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wiadomosc {
    int dzien, miesiac, rok;
    String godzina, user, ip, tekst;

    public Wiadomosc(String data, String godzina, String user, String ip, String tekst) {
        Pattern Pdzien = Pattern.compile("\\d\\d");
        Pattern Prok = Pattern.compile("\\d{4}");
        Matcher mdzien = Pdzien.matcher(data);
        Matcher mRok = Prok.matcher(data);
        mdzien.find(0);
        this.dzien = Integer.parseInt(mdzien.group());
        mdzien.find(2);
        this.miesiac = Integer.parseInt(mdzien.group());
        mRok.find(5);
        this.rok = Integer.parseInt(mRok.group());

        this.godzina = godzina;
        this.user = user;
        this.ip = ip;
        this.tekst = tekst;
    }

    public String toString() {
        return " " + dzien + " " + miesiac + " " + rok + " " + godzina + " " + user + " " + ip + " " + tekst;
    }
}
