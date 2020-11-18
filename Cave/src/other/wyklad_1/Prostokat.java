package other.wyklad_1;

import java.awt.*;

public class Prostokat extends Figura {

    int bok1, bok2;


    public Prostokat(int bok1, int bok2){
        this.bok1 = bok1;
        this.bok2 = bok2;

    }
    public Prostokat(int bok1, int bok2, int x, int y){
        this(bok1, bok2);
        this.x = x;
        this.y = y;
    }

    public void rysuj(Graphics g){
        g.drawRect(x,y,bok1,bok2);
    }
}
