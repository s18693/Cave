package other.GUI2;

public class Trojkat extends Figura {
    int height, a, b, c;

    public Trojkat(int a, int b, int c, int height, int color) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
        this.height = height;
        this.obwod = (a + b + c);
        float p = obwod / 2;
        this.pole = (float) (Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }
}
