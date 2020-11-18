package other.GUI2;

public class Kwadrat extends Figura {
    int a;

    public Kwadrat(int a, int color) {
        super(color);
        this.a = a;
        this.pole = a *a;
        this.obwod = 4*a;
    }
}
