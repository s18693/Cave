package Engine.gfx;

public class Font {
    public static final Font STANDARD = new Font("/Fonts/font.png");
    public static final Font FPS = new Font("/Fonts/fps.png");

    private Image fontimage;
    private int[] offsets, widths;

    public Font(String path) {
        fontimage = new Image(path);
        offsets = new int[100];
        widths = new int[100];

        int unicode = 0;

        for (int a = 0; a < fontimage.getW(); a++) {
            if (fontimage.getP()[a] == 0xff0000ff)
                offsets[unicode] = a;
            if (fontimage.getP()[a] == 0xffffff00) {
                widths[unicode] = a - offsets[unicode];
                unicode++;
            }
        }
    }

    public Image getFontimage() {
        return fontimage;
    }

    public void setFontimage(Image fontimage) {
        this.fontimage = fontimage;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    public int[] getWidths() {
        return widths;
    }

    public void setWidths(int[] widths) {
        this.widths = widths;
    }
}
