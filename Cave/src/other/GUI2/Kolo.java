package other.GUI2;

public class Kolo extends Figura {
    int promien;

    public Kolo(int promien, int color) {
        super(color);

        this.promien = promien;
        this.obwod = (float)(2 * Math.PI * promien);
        this.pole = (float)(Math.PI*promien*promien);
    }
}
