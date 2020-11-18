package PPJ23;

import java.io.*;
import java.util.Scanner;

public class PPJ23 {
    public static void main(String[] args) {
        System.out.print("Start");
        InputStream strumienWe = System.in;


        //ćwiczenie 1 vs //ćwiczenie 3
        StringBuffer sba = new StringBuffer();
        //sb.append(strumienWe);

        System.out.println();

        try {
            FileInputStream fis = new FileInputStream("Z:\\PPJ\\MyFiles\\1.txt");
            //InputStream strumienWe = new FileInputStream("c:\\data\\input-text.txt");
            StringBuilder sb = new StringBuilder();
            int wrt = fis.read();

            while (wrt != -1) {
                sb.append((char) wrt);
                wrt = fis.read();
            }

            System.out.print(sb);

        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
}
