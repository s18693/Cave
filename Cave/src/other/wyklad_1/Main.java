package other.wyklad_1;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends Frame {
    public static void main(String[] args) {
        new Main();
    }

    Wyrysowywalna tab[];
    int counter;

    public Main() {
        tab = new Wyrysowywalna[100];
        counter = 0;


        this.addMouseListener(new MouseAdapter() {
                                  public void mouseClicked(MouseEvent evt) {
                                      dodaj(Math.random() > 0.5 ? new Kwadrat(30, evt.getX(), evt.getY()) : new Prostokat(30, 40, evt.getX(), evt.getY()));
                                      repaint();
                                  }
                              }
        );

        dodaj(new Kwadrat(30));
        dodaj(new Prostokat(30, 40));

        this.setSize(640, 480);
        this.setVisible(true);

    }

    public void paint(Graphics g) {

        for (int i = 0; i < counter; i++) {
            tab[i].rysuj(g);
        }
    }

    public void dodaj(Wyrysowywalna f) {
        tab[counter++] = f;
    }
}
