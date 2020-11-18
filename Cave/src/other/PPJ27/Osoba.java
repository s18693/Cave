package other.PPJ27;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Osoba {
    private String imie, nazwisko, helper = "";
    private int rokUrodzenia;
    private boolean plec;
    private short kodPocztowy;
    private boolean isOk = true;
    private String sb = "1234567890";
    private StringBuffer sba = new StringBuffer();

    private boolean[] dane = new boolean[5];

    public Osoba() throws niewlasciweDaneExce {
        Scanner scanner = new Scanner(System.in);


        if (!dane[0]) {
            System.out.println("Imie: ");
            this.imie = scanner.nextLine();
            dane[0] = true;
        }
        if (!dane[1]) {
            System.out.println("Nazwisko: ");
            this.nazwisko = scanner.nextLine();
            dane[1] = true;
        }
        if (!dane[2]) {
            System.out.println("Rok: ");
            this.helper = scanner.nextLine();

            for (int a = 0; a < helper.length(); a++) {
                sba = new StringBuffer();
                sba.append(helper.charAt(a));
                System.out.println("Read: " + sba.toString());
                if (sb.contains(sba)) {
                    isOk = true;
                } else {
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                System.out.println("OK: ");
                this.rokUrodzenia = Integer.parseInt(helper);
                dane[2] = true;
            } else {
                System.out.println("NOT: ");
                throw new niewlasciweDaneExce();
            }
        }

        if (!dane[3]) {
            System.out.println("Plec: ");
            this.helper = scanner.nextLine();
            System.out.println(helper);
            if (helper.contentEquals("true")) {
                this.plec = true;
            } else if (helper.contentEquals("false")) {
                this.plec = false;
            } else
                throw new niewlasciweDaneExce();
        }
        if (!dane[4]) {
            System.out.println("KodPocztowy: ");

            this.helper = scanner.nextLine();

            for (int a = 0; a < helper.length(); a++) {
                sba = new StringBuffer();
                sba.append(helper.charAt(a));
                System.out.println("Read: " + sba.toString());
                if (sb.contains(sba)) {
                    isOk = true;
                } else {
                    isOk = false;
                    break;
                }
            }
            if (isOk) {
                this.kodPocztowy = scanner.nextShort();
                dane[4] = true;
            }
            else
                throw new niewlasciweDaneExce();
        }
    }

    public String toString(){
        return imie + " " + nazwisko + " " + rokUrodzenia + " " + plec + " " + kodPocztowy;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getRokUrodzenia() {
        return rokUrodzenia;
    }

    public boolean isPlec() {
        return plec;
    }

    public short getKodPocztowy() {
        return kodPocztowy;
    }
}
