package other.PPJ27;

import java.io.*;

public class PPJ27 {
    static public Osoba[] osoba;
    public static void main(String [] args){
        osoba = new Osoba[3];

        osoba[0] = new Osoba();

        try {
            createfile("file","Dane");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Z:\PPJ\file.bin
        writeToFile("Z:\\PPJ\\Dane.bin",osoba[0].toString());
    }

    public static void createFolder(String path) {
        File dir = new File(path);
        dir.mkdir();
    }

    public static void createfile(String path, String name) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(path + "/" + name + ".bin", "UTF-8");
        } catch (FileNotFoundException fnfe) {
            createFolder("file");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
