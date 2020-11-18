package other.PPJ26;

import java.io.*;

public class PPJ26 {
    static Color[] color = new Color[1000];
    static int r, g, b;

    public static void main(String[] args) {
        for (int a = 0; a < color.length; a++) {
            r = (int) (Math.random() * 255);
            g = (int) (Math.random() * 255);
            b = (int) (Math.random() * 255);
            int value = r << 16 | b << 8 | g;
            color[a] = new Color(value);
        }

        //cw2();

        readfile("D:\\moje rzeczy\\Java\\Cave\\file\\listaB.txt");
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

    public static void cw2() {
        try {
            createfile("file", "lista");
            createfile("file", "listaB");
            FileWriter fw = new FileWriter("D:\\moje rzeczy\\Java\\Cave\\file\\lista.txt");
            FileWriter fwb = new FileWriter("D:\\moje rzeczy\\Java\\Cave\\file\\listaB.txt");

            for (int a = 0; a < color.length; a++)
                fw.write(color[a].toString());
            for (int a = 0; a < color.length; a++)
                fwb.write(color[a].value);
            fw.close();
            fwb.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFolder(String path) {
        File dir = new File(path);
        dir.mkdir();
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

    public static void readfile(String path) {

        try {
            FileInputStream fis = new FileInputStream(path);
            StringBuilder sb = new StringBuilder();
            new Color(fis.read() << 16 | fis.read() << 8 | fis.read());

            byte wrt = (byte) fis.read();
            while (wrt != -1) {
                sb.append((char) wrt);
                wrt = (byte) fis.read();
            }

            System.out.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
