package other.PPJ25;

import java.io.*;
import java.util.ArrayList;

public class PPJ25 {
    public static int[] liczby1, dzialnie = new int[2];

    public static void main(String[] args) {
        //readfile("D:\\moje rzeczy\\Java\\Cave\\test\\zad1.txt");

        //ćwiczenie 1
        //readFirstNumber("D:\\moje rzeczy\\Java\\Cave\\test\\zad1.txt");
        //ćwiczenie 4
        readFileDzialanie("D:\\moje rzeczy\\Java\\Cave\\test\\zad4.txt");
        //cw3();
    }

    public static void cw3() {
        try {
            FileInputStream fis1 = new FileInputStream("D:\\\\moje rzeczy\\\\Java\\\\Cave\\\\test\\\\zad3-1.txt");
            FileInputStream fis2 = new FileInputStream("D:\\\\moje rzeczy\\\\Java\\\\Cave\\\\test\\\\zad3-2.txt");
            FileInputStream fis3 = new FileInputStream("D:\\\\moje rzeczy\\\\Java\\\\Cave\\\\test\\\\zad3-3.txt");

            int wrt1 = -1, wrt2 = -1, wrt3 = -1;
            wrt1 = fis1.read();
            wrt2 = fis2.read();
            wrt3 = fis3.read();

            while (wrt1 != -1 || wrt2 != -1 || wrt3 != -1) {
                if (wrt1 != -1) {
                    System.out.print((char) wrt1);
                    wrt1 = fis1.read();
                }
                if (wrt2 != -1) {
                    System.out.print((char) wrt2);
                    wrt2 = fis2.read();
                }
                if (wrt3 != -1) {
                    System.out.print((char) wrt3);
                    wrt3 = fis3.read();
                }
                //if(wrt1 == 32 || wrt2 == 32 || wrt3 == 32)
                //  System.out.println();
                //System.out.println(wrt1 + " " + wrt2 + " " + wrt3 + " ");
                /*
                wrt1 = fis1.read();
                wrt2 = fis2.read();
                wrt3 = fis3.read();
*/

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readfile(String path) {

        try {
            FileInputStream fis = new FileInputStream(path);
            StringBuilder sb = new StringBuilder();
            int wrt = fis.read();
            while (wrt != -1) {
                sb.append((char) wrt);
                wrt = fis.read();
            }

            System.out.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFirstNumber(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            StringBuilder sb = new StringBuilder();
            int wrt = fis.read();
            int cunter = 0;
            while (wrt != -1) {
                if (wrt == 32)
                    cunter++;
                wrt = fis.read();
            }
            System.out.println(" " + cunter);
            liczby1 = new int[cunter];

            int helper = 0;
            fis = new FileInputStream(path);
            wrt = fis.read();
            while (wrt != -1) {
                System.out.println(wrt);
                if (wrt == 32) {
                    liczby1[helper] = Integer.parseInt(sb.toString());
                    //System.out.println(wrt);
                    helper++;
                    wrt = fis.read();
                    sb = new StringBuilder();
                }
                sb.append((char) wrt);
                wrt = fis.read();
            }
            System.out.println("All checked");
            for (int a = 0; a < liczby1.length; a++)
                System.out.print(" " + liczby1[a]);
            int suma = 0;
            for (int a = 0; a < liczby1.length; a++)
                suma += liczby1[a];
            System.out.println();
            System.out.println(suma);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFileDzialanie(String path) {
        try {
            String[] newLine, ReadLine;
            int lines = 0, useline = 0;
            int operation = 0;
            boolean makeOp = false;
            FileInputStream fis = new FileInputStream(path);
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String HelpLine = "";
            //int lastchar = (int) line.charAt(line.length() - 1);
            newLine = new String[4];
            ReadLine = new String[4];

            ReadLine[useline] = reader.readLine();
            System.out.println(ReadLine[useline]);


            //System.out.println(ReadLine[useline]);
            int wrt = fis.read();
            //HelpLine += (char)wrt;

            int wynik = 0;
            int helper = 0;
            while (wrt != -1) {
                System.out.println(HelpLine + "<");
                System.out.println(ReadLine[useline] + "<<");
                if (HelpLine.contains(ReadLine[useline])) {
                    System.out.println(sb.toString() + "Operation");

                    dzialnie[helper] = Integer.parseInt(sb.toString());
                    helper = 0;
                    newLine[useline] = ReadLine[useline] + " = " + (dzialnie[0] + dzialnie[1]);
                    System.out.println(newLine[useline]);
                    dzialnie = new int[2];
                    HelpLine = "";
                    useline++;
                    wrt = fis.read();
                    wrt = fis.read();
                    sb = new StringBuilder();
                    ReadLine[useline] = reader.readLine();
                    System.out.println(ReadLine[useline]);
                }
                if (wrt == 32) {
                    HelpLine += (char) wrt;
                    System.out.println(sb.toString() + "Read");
                    if (sb.toString().contains("+")) {
                        operation = 0;
                        wrt = fis.read();
                        //HelpLine += " " + sb.toString();
                        sb = new StringBuilder();
                        makeOp = true;
                    } else if (sb.toString().contains("-")) {
                        wrt = fis.read();
                        //HelpLine += " " + sb.toString();
                        sb = new StringBuilder();
                        operation = 1;
                        makeOp = true;
                    } else if (sb.toString().contains("*")) {
                        operation = 2;
                        wrt = fis.read();
                        //HelpLine += " " + sb.toString();
                        sb = new StringBuilder();
                        makeOp = true;
                    } else if (sb.toString().contains("/")) {
                        operation = 3;
                        wrt = fis.read();
                        //HelpLine += " " + sb.toString();
                        sb = new StringBuilder();
                        makeOp = true;
                    } else {
                        //HelpLine += " " + sb.toString();
                        System.out.println(HelpLine + "BeforeOperation");
                        if (HelpLine.contains(ReadLine[useline])) {
                            System.out.println(sb.toString() + "Operation");
                            dzialnie[helper] = Integer.parseInt(sb.toString());
                            helper = 0;
                            newLine[useline] = ReadLine[useline] + " = " + (dzialnie[0] + dzialnie[1]);
                            System.out.println(newLine[useline]);
                            dzialnie = new int[2];
                            HelpLine = "";
                            ReadLine[useline] = reader.readLine();
                            useline++;

                        } else {
                            System.out.println(sb.toString() + "L");
                            dzialnie[helper] = Integer.parseInt(sb.toString());
                            helper++;
                            System.out.println(helper);
                            wrt = fis.read();

                            sb = new StringBuilder();
                        }//System.out.println(wrt);
                    }

                } else {
                    HelpLine += (char) wrt;
                    sb.append((char) wrt);
                    wrt = fis.read();
                    //HelpLine += (char)wrt;

                }
            }
            System.out.println("All checked");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
