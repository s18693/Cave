package other.GUI2;

abstract class Figura {
    float pole, obwod, color;

    public Figura(int color) {
        this.color = color;
    }

    public String toString() {
        return "P: " + pole + " L: " + obwod + " C: " + color;
    }
}
