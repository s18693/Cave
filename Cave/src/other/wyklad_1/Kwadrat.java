package other.wyklad_1;

import java.awt.*;

public class Kwadrat extends Figura{

    int bok;


    public Kwadrat(int bok){
        this.bok = bok;

    }

    public Kwadrat(int bok, int x, int y){
        this(bok);
        this.x = x;
        this.y = y;
    }

    public void rysuj(Graphics g){
        g.drawRect(x,y,bok,bok);
    }
}
