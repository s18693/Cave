package Engine.GameLoop;

import Engine.gfx.*;

import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Renderer {
    private Font font = Font.STANDARD, fontFPS = Font.FPS;
    private ArrayList<ImageRequest> imageRequests = new ArrayList<ImageRequest>();
    private ArrayList<LightRequest> lightRequests = new ArrayList<LightRequest>();

    private int pW, pH, zDepth = 0, ambientColor = -1; //0xff232323;
    private int[] p, zb, lm, lb;
    private boolean proccessing = false;
    private int camX, camY;


    public Renderer(GameContainer gc) {
        pW = Main.width;
        pH = Main.height;
        p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zb = new int[p.length];
        lm = new int[p.length];
        lb = new int[p.length];
    }

    public void clean() {
        for (int a = 0; a < p.length; a++) {
            p[a] = 0;
            zb[a] = 0;
            lm[a] = ambientColor;
            lb[a] = ambientColor;
        }
    }

    public void proccess() {
        proccessing = true;

        Collections.sort(imageRequests, new Comparator<ImageRequest>() {
            @Override
            public int compare(ImageRequest i0, ImageRequest i1) {
                if (i0.zDepth < i1.zDepth)
                    return 1;
                if (i0.zDepth > i1.zDepth)
                    return -1;
                return 0;
            }
        });


        for (int a = 0; a < imageRequests.size(); a++) {
            ImageRequest ir = imageRequests.get(a);

            setzDepth(ir.zDepth);
            //ir.image.setAlpha(false);
            drawImage(ir.image, ir.offX, ir.offY);
            //System.out.println(ir.zDepth);
        }


        for (int a = 0; a < lightRequests.size(); a++) {
            LightRequest l = lightRequests.get(a);
            drawLightRequest(l.light, l.locX, l.locY);
        }

        for (int a = 0; a < p.length; a++) {
            float r = ((lm[a] >> 16) & 0xff) / 255f;
            float g = ((lm[a] >> 8) & 0xff) / 255f;
            float b = (lm[a] & 0xff) / 255f;

            p[a] = ((int) (((p[a] >> 16) & 0xff) * r) << 16 | (int) (((p[a] >> 8) & 0xff) * g) << 8 | (int) ((p[a] & 0xff) * b));
        }


        imageRequests.clear();
        lightRequests.clear();
        proccessing = false;
    }

    public void setPixel(int x, int y, int value) {
        //value == 0xffff00ff - dont read voilet color in image (now dont show alfa color)

        int alpha = ((value >> 24) & 0xff);

        if ((x < 0 || x >= pW || y < 0 || y >= pH) || alpha == 0) {
            return;
        }

        int index = x + y * pW;

        if (zb[index] > zDepth)
            return;


        zb[index] = zDepth;

        if (alpha == 255) {
            p[index] = value;
        } else {
            int pixelColor = p[index];

            /*
            int readR = (pixelColor >> 16) & 0xff;
            int readG = (pixelColor >> 8) & 0xff;
            int readB = (pixelColor & 0xff);

            int valR = (value >> 16) & 0xff;
            int valG = (value >> 8) & 0xff;
            int valB = (value & 0xff);
*/
            int newRed = (int) (((pixelColor >> 16) & 0xff) + ((((value >> 16) & 0xff) - ((pixelColor >> 16) & 0xff)) * (alpha / 255F)));
            int newGreen = (int) (((pixelColor >> 8) & 0xff) + ((((value >> 8) & 0xff) - ((pixelColor >> 8) & 0xff)) * (alpha / 255F)));
            int newBlue = (int) ((pixelColor & 0xff) + (((value & 0xff) - (pixelColor & 0xff)) * (alpha / 255F)));


            p[index] = (newRed << 16 | newGreen << 8 | newBlue);

            //System.out.println(readR + " R " + newRed + " " + readG + " G " + newGreen + " " + readB + " B " + newBlue + " " + alpha + " " + valR + " " + valG + " " + valB);
        }
    }

    public void drawImage(Image image, int offX, int offY) {
        if (image.isAlpha() && !proccessing) {
            imageRequests.add(new ImageRequest(image, offX, offY, zDepth));
            return;
        }

        offX -= camX;
        offY -= camY;
        int newX = 0, newY = 0, newWidth = image.getW(), newHeight = image.getH();


        //dont render out of frame
        /*
        if (newWidth + offX > pW)
            newWidth -= (newWidth + offX - pW);
        if (newHeight + offY > pH)
            newHeight -= (newHeight + offY - pH);

        if (offX < -newWidth) return;
        if (offX >= pW) return;
        if (offY < -newHeight) return;
        if (offY >= pH) return;

        if (newX < 0)
            newX -= newX;
        if (newY < 0)
            newY -= newY;
*/
        for (int y = newY; y < newHeight; y++)
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
                setLightBlock(x + offX, y + offY, image.getLightBlock());
            }
    }

    public void drawImage(Image image, int offX, int offY, int q, int rotation) {
        if (image.isAlpha() && !proccessing) {
            imageRequests.add(new ImageRequest(image, offX, offY, zDepth));
            return;
        }

        offX -= camX;
        offY -= camY;
        int newX = 0, newY = 0, newWidth = image.getW(), newHeight = image.getH();


        //dont render out of frame
        /*
        if (newWidth + offX > pW)
            newWidth -= (newWidth + offX - pW);
        if (newHeight + offY > pH)
            newHeight -= (newHeight + offY - pH);

        if (offX < -newWidth) return;
        if (offX >= pW) return;
        if (offY < -newHeight) return;
        if (offY >= pH) return;

        if (newX < 0)
            newX -= newX;
        if (newY < 0)
            newY -= newY;
*/


        for (int x = -image.getH(); x < q; x++) {
            for (int y = -image.getW(); y < q; y++) {

                final double radians = Math.toRadians(rotation);
                final double cos = Math.cos(radians);
                final double sin = Math.sin(radians);

                final int centerx = image.getW() / 2;
                final int centery = image.getH() / 2;
                final int m = x - centerx;
                final int n = y - centery;
                final int j = ((int) (m * cos + n * sin)) + centerx;
                final int k = ((int) (n * cos - m * sin)) + centery;

                if (j >= 0 && j < image.getW() && k >= 0 && k < image.getH()) {
                    setPixel(x + offX, y + offY, image.getP()[j + k * image.getW()]);
                }

            }

        }
    }

    public void drawImage(Image image, int offX, int offY, int q, double sinus, double cosinus) {
        if (image.isAlpha() && !proccessing) {
            imageRequests.add(new ImageRequest(image, offX, offY, zDepth));
            return;
        }

        offX -= camX;
        offY -= camY;
        int newX = 0, newY = 0, newWidth = image.getW(), newHeight = image.getH();


        //dont render out of frame
        /*
        if (newWidth + offX > pW)
            newWidth -= (newWidth + offX - pW);
        if (newHeight + offY > pH)
            newHeight -= (newHeight + offY - pH);

        if (offX < -newWidth) return;
        if (offX >= pW) return;
        if (offY < -newHeight) return;
        if (offY >= pH) return;

        if (newX < 0)
            newX -= newX;
        if (newY < 0)
            newY -= newY;
*/


        for (int x = -image.getH(); x < q; x++) {
            for (int y = -image.getW(); y < q; y++) {

                final double sin = sinus;
                final double cos = cosinus;


                final int centerx = image.getW() / 2;
                final int centery = image.getH() / 2;
                final int m = x - centerx;
                final int n = y - centery;
                final int j = ((int) (m * cos + n * sin)) + centerx;
                final int k = ((int) (n * cos - m * sin)) + centery;

                if (j >= 0 && j < image.getW() && k >= 0 && k < image.getH()) {
                    setPixel(x + offX, y + offY, image.getP()[j + k * image.getW()]);
                }

            }

        }
    }

    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
        if (image.isAlpha() && !proccessing) {
            imageRequests.add(new ImageRequest(image.getTileImage(tileX, tileY), offX, offY, zDepth));
            return;
        }


        offX -= camX;
        offY -= camY;

        int newX = 0, newY = 0, newWidth = image.getTileW(), newHeight = image.getTileH();

        if (offX < -newWidth) return;
        if (offX >= pW) return;
        if (offY < -newHeight) return;
        if (offY >= pH) return;

        if (newX < 0)
            newX -= newX;
        if (newY < 0)
            newY -= newY;
        if (newWidth + offX > pW)
            newWidth -= (newWidth + offX - pW);
        if (newHeight + offY > pH)
            newHeight -= (newHeight + offY - pH);


        for (int y = newY; y < newHeight; y++)
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offX, y + offY, image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);
                setLightBlock(x + offX, y + offY, image.getLightBlock());
            }
    }

    public void setLightBlock(int x, int y, int value) {
        x -= camX;
        y -= camY;

        if (x < 0 || x >= pW || y < 0 || y >= pH) {
            return;
        }

        if (zb[x + y * pW] > zDepth)
            return;

        lb[x + y * pW] = value;
    }

    public void drawText(String text, int offX, int offY, int color) {
        offX -= camX;
        offY -= camY;

        text = text.toUpperCase();
        int offset = 0;

        for (int a = 0; a < text.length(); a++) {
            int unicode = text.codePointAt(a) - 64;

            for (int y = 0; y < font.getFontimage().getH(); y++) {
                for (int x = 0; x < font.getWidths()[unicode]; x++) {
                    if (font.getFontimage().getP()[(x + font.getOffsets()[unicode]) + (y * font.getFontimage().getW())] == 0xffffffff)
                        setPixel(x + offX + offset, y + offY, color);
                }
            }
            offset += font.getWidths()[unicode];
        }
    }

    public void drawLight(Light l, int offX, int offY) {
        lightRequests.add(new LightRequest(l, offX, offY));
    }

    private void drawLightRequest(Light l, int offX, int offY) {
        offX -= camX;
        offY -= camY;

        for (int a = 0; a <= l.getDiameter(); a++) {
            drawLightLine(l, l.getRadius(), l.getRadius(), a, 0, offX, offY);
            drawLightLine(l, l.getRadius(), l.getRadius(), a, l.getDiameter(), offX, offY);
            drawLightLine(l, l.getRadius(), l.getRadius(), 0, a, offX, offY);
            drawLightLine(l, l.getRadius(), l.getRadius(), l.getDiameter(), a, offX, offY);
        }
    }

    private void drawLightLine(Light l, int x0, int y0, int x1, int y1, int offX, int offY) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int e2;
        while (true) {
            int screenX = x0 - l.getRadius() + offX;
            int screenY = y0 - l.getRadius() + offY;

            if (screenX < 0 || screenX >= pW || screenY < 0 || screenY >= pH)
                return;

            int lightColor = l.getLightValue(x0, y0);
            if (lightColor == 0)
                return;

            if (lb[screenX + screenY * pW] == Light.FULL)
                return;


            setLightMap(screenX, screenY, lightColor);


            if (x0 == x1 && y0 == y1)
                break;
            e2 = 2 * err;

            if (e2 > -1 * dy) {
                err -= dy;
                x0 += sx;
            }

            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public void setLightMap(int x, int y, int value) {
        x -= camX;
        y -= camY;
        if ((x < 0 || x >= pW || y < 0 || y >= pH)) {
            return;
        }

        int baseColor = lm[x + y * pW];

        int maxRed = Math.max((baseColor >> 16) & 0xff, (value >> 16) & 0xff);
        int maxGreen = Math.max((baseColor >> 8) & 0xff, (value >> 8) & 0xff);
        int maxBlue = Math.max((baseColor) & 0xff, value & 0xff);

        lm[x + y * pW] = (maxRed << 16 | maxGreen << 8 | maxBlue);
    }

    public void drawFPS(String text, int offX, int offY, int color) {
        text = text.toUpperCase();
        int offset = 0;

        for (int a = 0; a < text.length(); a++) {
            int unicode = text.codePointAt(a);
            //list

            switch (unicode) {
                case 70:
                    unicode = 1;//F
                    break;
                case 80:
                    unicode = 2;//P
                    break;
                case 83:
                    unicode = 3;//S
                    break;
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    unicode = unicode - 44;//numbers
                    break;
                case 32:
                    unicode = 14;//space

            }


            //System.out.println(unicode);
            for (int y = 0; y < fontFPS.getFontimage().getH(); y++) {
                for (int x = 0; x < fontFPS.getWidths()[unicode]; x++) {
                    if (fontFPS.getFontimage().getP()[(x + fontFPS.getOffsets()[unicode]) + (y * fontFPS.getFontimage().getW())] == 0xffffffff)
                        setPixel(x + offX + offset, y + offY, color);
                }
            }
            offset += fontFPS.getWidths()[unicode];
        }
    }

    public void drawIcons(Image image, int amount, int number, int color) {
        int unicode;
        int offset = 0;
        String text = Integer.toString(amount);

//icon
        for (int y = 0; y < image.getH(); y++)
            for (int x = 0; x < image.getW(); x++) {
                setPixel(x + 60 + 40 * number, y + 4, image.getP()[x + y * image.getW()]);
            }
        //numbers
        if (amount < 10)
            text = "0" + amount;
        for (int a = 0; a < 2; a++) {
            unicode = text.codePointAt(a);
            //list
            unicode = unicode - 44;//numbers
            for (int y = 0; y < fontFPS.getFontimage().getH(); y++) {
                for (int x = 0; x < fontFPS.getWidths()[unicode]; x++) {
                    if (fontFPS.getFontimage().getP()[(x + fontFPS.getOffsets()[unicode]) + (y * fontFPS.getFontimage().getW())] == 0xffffffff)
                        setPixel(x + 35 + 40 * number + offset, y, color);
                }
            }
            offset += fontFPS.getWidths()[unicode];
        }
    }

    public void drawFuel(float amount, float maxFuel, int color) {
        float help = amount / maxFuel;
        for (int a = 0; a < help * 100; a++)
            for (int b = 0; b < 5; b++)
                setPixel(Main.width - 101 + a, Main.height - 11 + b, color);
    }

    public void drawRect(int offX, int offY, int width, int height, int color) {

        offX -= camX;
        offY -= camY;

        int newX = 0, newY = 0, newWidth = width, newHight = height;

        if (offX < -newWidth) return;
        if (offX >= pW) return;
        if (offY < -newHight) return;
        if (offY >= pH) return;

        if (newX < 0)
            newX -= newX;
        if (newY < 0)
            newY -= newY;
        if (newWidth + offX > pW)
            newWidth -= (newWidth + offX - pW);
        if (newHight + offY > pH)
            newHight -= (newHight + offY - pH);


        for (int y = newY; y <= newHight; y++) {
            setPixel(offX, y + offY, color);
            setPixel(offX + width, y + offY, color);
        }
        for (int x = newX; x <= newWidth; x++) {
            setPixel(x + offX, offY, color);
            setPixel(x + offX, offY + height, color);
        }
    }

    public void drawRect(int offX, int offY, int width, int height, int color, int size) {

        offX -= camX;
        offY -= camY;

        int newX = 0, newY = 0, newWidth = width, newHight = height;

        if (offX < -newWidth) return;
        if (offX >= pW) return;
        if (offY < -newHight) return;
        if (offY >= pH) return;

        if (newX < 0)
            newX -= newX;
        if (newY < 0)
            newY -= newY;
        if (newWidth + offX > pW)
            newWidth -= (newWidth + offX - pW);
        if (newHight + offY > pH)
            newHight -= (newHight + offY - pH);

        for (int a = 0; a < size; a++) {
            for (int y = newY; y <= newHight + size / 2; y++) {
                setPixel(offX + a, y + offY, color);
                setPixel(offX + width + a, y + offY, color);
            }
        }
        for (int a = 0; a < size; a++) {
            for (int x = newX; x <= newWidth + size / 2; x++) {
                setPixel(x + offX, offY + a, color);
                setPixel(x + offX, offY + height + a, color);
            }
        }
    }

    public void drawFillRect(int offX, int offY, int width, int height, int color) {
        offX -= camX;
        offY -= camY;

        int newX = 0, newY = 0, newWidth = width, newHight = height;

        //dont render out of frame

        if (offX < -newWidth) return;
        if (offX >= pW) return;
        if (offY < -newHight) return;
        if (offY >= pH) return;

        if (newX < 0)
            newX -= newX;
        if (newY < 0)
            newY -= newY;
        if (newWidth + offX > pW)
            newWidth -= (newWidth + offX - pW);
        if (newHight + offY > pH)
            newHight -= (newHight + offY - pH);


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setPixel(x + offX, y + offY, color);
            }
        }
    }

    public int getzDepth() {
        return zDepth;
    }

    public void setzDepth(int zDepth) {
        this.zDepth = zDepth;
    }

    public int getCamX() {
        return camX;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }

    public int getCamY() {
        return camY;
    }

    public void setCamY(int camY) {
        this.camY = camY;
    }
}
