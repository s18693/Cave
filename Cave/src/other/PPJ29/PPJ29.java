package other.PPJ29;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PPJ29 {

    public static String[][] dane = new String[101][3];
    public static StringBuilder sb;
    public static int index = 0;
    public static Wiadomosc wiadomosc;

    public static void main(String[] args) {
        cw1();
        cw3();
        wiadomosc = new Wiadomosc("20/21/2018", "12:50", "lukonito", "198.162.8.1", "Hellow Word");
        /*
        for (int a = 0; a < dane.length; a++) {
            wiadomosc()
        }
       */
    }

    public static void cw1() {
        try {
            FileInputStream fis = new FileInputStream("Z:\\PPJ\\MyFiles\\serverLog.txt");
            sb = new StringBuilder();
            int wrt = fis.read();
            while (wrt != -1) {
                sb.append((char) wrt);
                wrt = fis.read();
            }

            //System.out.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("Start");

        Pattern IP = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
        Pattern Date = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Pattern Message = Pattern.compile("> +\\w");

        Matcher mIp = IP.matcher(sb.toString());
        Matcher mDate = Date.matcher(sb.toString());
        Matcher mMessage = Message.matcher(sb.toString());

        int start = 0;
        while (mIp.find(start) && start >= 0 && index < dane.length) {
            dane[index][0] = mIp.group();
            start = sb.toString().indexOf(mIp.group(), start);
            start += start >= 0 ? mIp.group().length() + 1 : 0;
            index++;
        }
        index = 0;
        start = 0;
        while (mDate.find(start) && start >= 0 && index < dane.length) {
            dane[index][1] = mDate.group();
            start = sb.toString().indexOf(mDate.group(), start);
            start += start >= 0 ? mIp.group().length() + 1 : 0;
            index++;
        }
        index = 0;
        start = 0;
        while (mMessage.find(start) && start >= 0 && index < dane.length) {
            dane[index][2] = mMessage.group();
            start = sb.toString().indexOf(mMessage.group(), start);
            start += start >= 0 ? mIp.group().length() + 1 : 0;
            index++;
        }
        //System.out.println(mIp.group());

        //dane[index][0] = mIp.group();
        //dane[index][1] = mDate.group();
        //dane[index][2] = mMessage.group();

    }

    public static void cw3() {
        try {
            FileInputStream fis = new FileInputStream("Z:\\PPJ\\MyFiles\\serverLog.txt");
            sb = new StringBuilder();
            int wrt = fis.read();
            while (wrt != -1) {
                sb.append((char) wrt);
                wrt = fis.read();
            }

            System.out.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Start");

        Pattern IP = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
        Pattern Date = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Pattern Message = Pattern.compile("> +\\w");
        Pattern Godzina = Pattern.compile("\\d{2}:\\d{2}:\\d{2}");
        Pattern User = Pattern.compile("(-\\s\\w+)");

        Matcher mIp = IP.matcher(sb.toString());
        Matcher mDate = Date.matcher(sb.toString());
        Matcher mMessage = Message.matcher(sb.toString());
        Matcher mGodzina = Godzina.matcher(sb.toString());
        Matcher mUser = User.matcher(sb.toString());

        int start = 0;
        int start1 = 0;
        index = 0;
        while (mUser.find(start1) && mGodzina.find(start) && start >= 0) {
            wiadomosc = new Wiadomosc(dane[index][1],mGodzina.group(),mUser.group(),dane[index][0],dane[index][2]);

            start = sb.toString().indexOf(mGodzina.group(), start);
            start += start >= 0 ? mIp.group().length() + 1 : 0;

            start1 = sb.toString().indexOf(mUser.group(), start);
            start1 += start >= 0 ? mIp.group().length() + 1 : 0;
            index++;
        }
    }
}
