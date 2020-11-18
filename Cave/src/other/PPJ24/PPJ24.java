package other.PPJ24;

import java.io.*;

public class PPJ24 {
    public static void main(String[] args) throws FileNotFoundException {
        //cwiczenie();
        //readfile("Z:\\PPJ\\MyFiles\\PPJ24.class");
        //cwiczenie4(10);
        //("D:\\moje rzeczy\\Java\\Cave\\file\\lista.txt");

        /*
        String a = "ok_ijmie1♫";
        try {
            FileWriter fw = new FileWriter("D:\\\\moje rzeczy\\\\Java\\\\Cave\\\\file\\\\lista.txt");
            fw.write(a);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

*/

        createfile("file","Ziom");

    }
    public static void cwiczenie() {
        int countPirwsze = 0;
        int count = 2;


        try {
            FileWriter fw = new FileWriter("Z:\\PPJ\\MyFiles\\1.txt");
            while (countPirwsze < 20) {
                if (liczbaPierwsza(count)) {
                    countPirwsze++;
                    fw.write("" + count + " ");
                }
                count++;
            }
            fw.close();

            FileInputStream fis = new FileInputStream("Z:\\PPJ\\MyFiles\\1.txt");
            StringBuilder sb = new StringBuilder();
            int wrt = fis.read();
            //System.out.println(wrt);

            while (wrt != -1) {
                if (wrt == 32) {
                    if (!liczbaPierwsza(Integer.parseInt(sb.toString())))
                        System.out.println("false");
                    wrt = fis.read();
                    sb = new StringBuilder();
                }
                sb.append((char) wrt);
                wrt = fis.read();
            }
            System.out.println("All checked");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cwiczenie4(int amount) throws FileNotFoundException {
        int[] liczby = new int[amount];
        int counter = 0, helper = 1000, value = 0;
        for (int a = 0; a < liczby.length; a++)
            liczby[a] = (int) (Math.random() * 1000);

        for (int b = 0; b < liczby.length; b++) {
            helper = 1000;
            for (int a = b; a < liczby.length; a++) {
                if (helper > liczby[a]) {
                    helper = liczby[a];
                    counter = a;
                }
                if (a == liczby.length - 1) {
                    value = liczby[b];
                    liczby[b] = liczby[counter];
                    liczby[counter] = value;
                }
            }
        }

        for (int a = 0; a < liczby.length; a++)
            createfile("inputData", a);
        for (int a = 0; a < liczby.length; a++)
            writeToFile("D:\\moje rzeczy\\Java\\Cave\\file\\inputData" + a + ".txt", String.valueOf(liczby[a]));


    }

    public static void writeToFile(String path, String word) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(word);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFolder(String path) {
        File dir = new File(path);
        dir.mkdir();
    }

    public static void createfile(String name, int counter) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter("file/" + name + counter + ".txt", "UTF-8");
        } catch (FileNotFoundException fnfe) {
            createFolder("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeToFile(String path, int number) {
        try {
            FileWriter fw = new FileWriter(path);
            fw.write(number);
            fw.close();
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

    public static void createfile(String path, String name) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(path + "/" + name + ".txt", "UTF-8");
        } catch (FileNotFoundException fnfe) {
            createFolder("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getValu(FileInputStream fis) {
        int v1 = 0;
        int v2 = 0;
        try {
            v1 = fis.read();
            v2 = fis.read();

            v2 <<= 8;
            v2 += v1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return v2;
    }

    public static boolean liczbaPierwsza(int liczba) {
        int a = 2;
        while ((liczba % a != 0))
            a++;
        if (a == liczba)
            return true;
        return false;
    }
}
