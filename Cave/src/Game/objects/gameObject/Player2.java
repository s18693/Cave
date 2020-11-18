package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;
import Game.compoments.Vector2D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class Player2 extends GameObject {

    private float speed = 200, fallSpeed = 0, fallDistance = 0, fireRate, fireTick = 0, shieldRegeneration = 200, shieldTick = 0, damageTick = 0;
    private boolean TakeDamage = false, AddFuel = false;
    private Image p0, p1, p2, IPlayer, IShield, IVoid;
    private Image IconSheld, IconArmor;
    private int fps = 0, score = 0, armor = 36, shield = 0, shieldMax;
    double x, y, c, sinus, cosinus;


    private final static int SERVER_PORT = 10000;
    private final static String SERVER_HOSTNAME = "localhost";
    BufferedReader br;
    BufferedWriter bw;

    public Player2(GameMenager gm, int posX, int posY) {
        this.tag = "player";
        this.width = 16;
        this.height = 16;
        this.posX = posX;
        this.posY = posY;
        this.fireRate = 10;
        this.shieldMax = 5;
        this.IPlayer = new Image("/Player/player.png");
        this.p0 = new Image("/Player/animation/p0.png");
        this.p1 = new Image("/Player/animation/p1.png");
        this.p2 = new Image("/Player/animation/p2.png");
        this.IShield = new Image("/Player/shield.png");
        this.IconSheld = new Image("/Icons/shield.png");
        this.IconArmor = new Image("/Icons/armor.png");
        this.IVoid = new Image("/void16.png");
        this.imageMain = IPlayer;
        IShield.setAlpha(true);
        this.addCompoment(new AABBCompoment(this, gm));


    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        if (gc.getInput().isKey(KeyEvent.VK_W)) {
            //animacja
            fps++;
            for (int a = 0; a < width; a++) {
                y = (gc.getInput().getMouseY() - posY);
                if (gm.getCollision((int) posX + a, (int) posY) && y <= 0) {

                } else if (gm.getCollision((int) posX + a, (int) posY + height) && y >= 0) {

                } else {
                    x = (gc.getInput().getMouseX() - posX);
                    c = (Math.sqrt(((y * y) + (x * x))));
                    sinus = y / c;
                    cosinus = x / c;
                    posX += cosinus * (dt * speed / width);
                    posY += sinus * (dt * speed / width);
                }
            }
        }
        if (!gc.getInput().isKey(KeyEvent.VK_W)) {
            imageMain = IPlayer;
            fps = 0;
        }
        if (gc.getInput().isKey(KeyEvent.VK_S)) {
            for (int a = 0; a < width; a++) {
                y = (gc.getInput().getMouseY() - posY);
                if (gm.getCollision((int) posX + a, (int) posY) && y >= 0) {

                } else if (gm.getCollision((int) posX + a, (int) posY + height) && y <= 0) {

                } else {
                    x = (gc.getInput().getMouseX() - posX);
                    c = (Math.sqrt(((y * y) + (x * x))));
                    sinus = y / c;
                    cosinus = x / c;
                    posX -= cosinus * (dt * speed / width);
                    posY -= sinus * (dt * speed / width);
                }
            }
        }
        if (gc.getInput().isKey(KeyEvent.VK_D)) {
            try {
                Socket clientSocket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
                InputStream is = null;
                is = clientSocket.getInputStream();
                OutputStream os = clientSocket.getOutputStream();
                //RAW - surowy, same bajty
                InputStreamReader isr = new InputStreamReader(is);
                OutputStreamWriter osw = new OutputStreamWriter(os);
                br = new BufferedReader(isr);
                bw = new BufferedWriter(osw);

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.write("GO LEFT");
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int a = 0; a < width; a++) {
                if (!gm.getCollision((int) posX + a, (int) posY + height)) {
                    posY += dt * speed / width;
                }
            }
        }

        if (gc.getInput().isKey(KeyEvent.VK_A)) {
            for (int a = 0; a < width; a++) {
                if (!gm.getCollision((int) posX + a, (int) posY)) {
                    posY -= dt * speed / width;
                }
            }
        }
        //fire
        if (gc.getInput().isButton(MouseEvent.BUTTON1)) {
            fireTick++;
            if (fireTick > fireRate) {
                x = (gc.getInput().getMouseX() - posX);
                y = (gc.getInput().getMouseY() - posY);
                c = (Math.sqrt(((y * y) + (x * x))));
                sinus = y / c;
                cosinus = x / c;
                Vector2D vector2D = new Vector2D(cosinus, sinus, 500);
                gm.addObject(new Bullet(gm, (int) posX, (int) posY, vector2D, "playerBullet"));
                fireTick = 0;
            }
        }
        //Block move Back
        for (int a = 0; a < height; a++) {
            if (gm.getCollision((int) posX + width, (int) posY + a)) {
                posX -= 1;
            }
        }
        this.updateCompoments(gc, gm, dt);
        //shield
        if (shield < shieldMax)
            shieldTick++;
        if (shieldTick >= shieldRegeneration) {
            shield++;
            shieldTick = 0;
        }
        //Dont take mutli damage - player have 10 = long brake
        if (!TakeDamage)
            damageTick++;
        if (damageTick > 10) {
            imageMain = IPlayer;
            TakeDamage = true;
            damageTick = 0;
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.setzDepth(1);
        r.drawIcons(IconSheld, shield, 1, 0xff0000ff);
        r.drawIcons(IconArmor, armor, 2, 0xffffff00);
        if (gc.getInput().getMouseX() >= posX) {
            x = (gc.getInput().getMouseX() - posX);
            y = (gc.getInput().getMouseY() - posY);
            c = (Math.sqrt(((y * y) + (x * x))));
            sinus = y / c;
            cosinus = x / c;

        }
        if (gc.getInput().getMouseX() < posX) {
            x = (gc.getInput().getMouseX() - posX);
            y = (gc.getInput().getMouseY() - posY);
            c = (Math.sqrt(((y * y) + (x * x))));
            sinus = y / c;
            cosinus = x / c;

        }
        r.setzDepth(10);
        r.drawImage(imageMain, (int) posX, (int) posY, IPlayer.getQ(), sinus, cosinus);
        r.setzDepth(0);
        if (shield > 0)
            r.drawImage(IShield, (int) posX - width / 2, (int) posY - height / 2);
        /*
        if (fps < 5)
            imageMain = p0;
        else if (fps < 10)
            imageMain = p1;
        else if (fps < 15)
            imageMain = p2;
        else
            fps = 0;
*/
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {
        if (other.getTag().equalsIgnoreCase("enemyBullet")) {
            if (TakeDamage) {
                if (shield > 0)
                    shield--;
                else if (armor > 0) {
                    imageMain = IVoid;
                    armor--;
                } else if (armor == 0 && shield == 0) {
                    System.out.println(score);
                    //setDead(true);
                }
                TakeDamage = false;
            }
        }
        if (other.getTag().equalsIgnoreCase("fuel")) {
            other.setDead(true);
            gm.addFuel((float) (Math.random() * 10));
        }
        if (other.getTag().equalsIgnoreCase("armor")) {
            other.setDead(true);
            armor++;
        }
    }

    public void addScore(int score) {
        this.score += score;
        //System.out.println(this.score + " " + shield);
    }
}
