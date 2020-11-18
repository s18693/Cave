package PPJ23;

import java.io.IOException;
import java.io.InputStream;

public class MojSkaner {
    InputStream is;

    public MojSkaner(InputStream is) {
        this.is = is;
    }

    public boolean whiteChar(char wrt) {
        String wc = " \n\t,.";
        for (int a = 0; a < wc.length(); a++) {
            if (wc.charAt(a) == wrt)
                return true;
        }
        return false;
    }

    public String odczytajslowo() {
        int odczytanaWrtosc;
        try {
            String str = "";
            while(!whiteChar((char)(odczytanaWrtosc = is.read())))
                str += (char)odczytanaWrtosc;
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void odczytajlinnie() {
        try {
            System.out.print(" " + is.read() + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int odczytajLiczbeCalkowita() {
        return Integer.parseInt(odczytajslowo());
    }
}
