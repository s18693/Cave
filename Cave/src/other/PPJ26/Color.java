package other.PPJ26;

public class Color {
    int value;

    public Color(int value) {
        int r = (((value >> 16) & 0xff) << 16);
        int g = (((value >> 8) & 0xff) << 8);
        int b = (value & 0xff);
        this.value = ((((value >> 16) & 0xff) << 16) | (((value >> 8) & 0xff) << 8) | (value & 0xff));
    }

    public String toString() {
        return " R: " + ((value >> 16) & 0xff) + " G: " + ((value >> 8) & 0xff) + " B: " + (value & 0xff);
    }
}
