package Game;

import Engine.GameLoop.AbstractGame;
import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Main;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Engine.gfx.Light;
import Game.objects.gameObject.Camera;
import Game.compoments.GameObject;
import Game.objects.gameObject.GameLogic;
import Game.objects.gameObject.Player;
import Game.objects.gameObject.WallLogic;
import Game.objects.mainMenu.MenuButtonLogic;
import com.sun.management.GarbageCollectionNotificationInfo;


import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.management.GarbageCollectorMXBean;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GameMenager extends AbstractGame {

    private boolean[] collision;
    private int levelW, levelH;
    ArrayList<GameObject> objects = new ArrayList<GameObject>();
    Light light;
    Image backgorund;
    WallLogic wallLogic;
    GameLogic gameLogic;
    private Player player;
    public int level = 1;
    public float fuel, useFuel, maxFuel;

    public float speed = 0, maxSpeed = 4, minSpeed = 1, speedBonus = 0.01f;
    //Camera camera;
    private boolean game = false, gameGo = false;

    //Server
    ServerSocket welcomeSocked;
    Socket clientSocket;
    InputStream is;
    OutputStream os;
    InputStreamReader isr;
    OutputStreamWriter osw;
    BufferedReader br;
    BufferedWriter bw;
    public String state;
    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new GameMenager());
        gc.start();
    }

    public GameMenager() {
        //objects.add(new Player(16, 16));
        //objects.add(new Platform());
        loadLevel("/levels/level1.png");

        //light = new Light(500, 0x00ffffff);
        backgorund = new Image("/background.png");
        objects.add(new MenuButtonLogic());
        fuel = 0;
        maxFuel = 100;
        useFuel = 0.1f;
        //camera = new Camera(2, "");

    }

    @Override
    public void start(GameContainer gc) {

    }

    public void loadLevel(String path) {
        Image levelImage = new Image(path);
        levelW = levelImage.getW();
        levelH = levelImage.getH();
        collision = new boolean[levelImage.getH() * levelImage.getW()];
        for (int y = 0; y < levelImage.getH(); y++) {
            for (int x = 0; x < levelImage.getW(); x++) {
                if (levelImage.getP()[x + y * levelImage.getW()] == 0xff000000)
                    collision[x + y * levelImage.getW()] = true;
                else
                    collision[x + y * levelImage.getW()] = false;
            }
        }
    }

    @Override
    public void update(GameContainer gc, float dt) {
        Physics.update();
        for (int a = 0; a < objects.size(); a++) {
            objects.get(a).update(gc, this, dt);
            if (objects.get(a).isDead()) {
                objects.remove(a);
                a--;
            }
        }

        //camera.update(gc, this, dt);
        if (gameGo) {
            if (gc.getInput().isKey(KeyEvent.VK_SHIFT) && fuel > 0) {
                fuel -= useFuel;
                speed += speedBonus;
            }
            if (speed > 3)
                speed -= 0.005f;
            else if (speed > 2)
                speed -= 0.003f;
            else
                speed -= 0.001f;
            if (speed > maxSpeed)
                speed = maxSpeed;
            if (speed < minSpeed)
                speed = minSpeed;
            //speed = 1;
            wallLogic.setSpeed((int) speed);
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        clientSocket = welcomeSocked.accept();
                        is = clientSocket.getInputStream();
                        os = clientSocket.getOutputStream();
                        //RAW - surowy, same bajty
                        isr = new InputStreamReader(is);
                        osw = new OutputStreamWriter(os);
                        br = new BufferedReader(isr);
                        bw = new BufferedWriter(osw);
                        state = br.readLine();
                        if(state != null)
                            System.out.println("[S]: " + state);
                        state = null;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            thread.start();
        }

        if (game) {
            gameLogic = new GameLogic(this);
            this.player = new Player(this, Main.width / 2, Main.height / 2);
            objects.add(gameLogic);
            objects.add(player);
            int size = 20 + (int) (Math.random() * 10);
            this.wallLogic = new WallLogic(this, Main.width, 0, size);
            objects.add(wallLogic);

            //Start server
            try {

                welcomeSocked = new ServerSocket(10000);


            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(game);
            gameGo = true;
            game = false;

        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(backgorund, 0, 0);
        //camera.render(r);
        /*
        for (int y = 0; y < levelH; y++) {
            for (int x = 0; x < levelW; x++) {
                if (collision[x + y * levelW])
                    r.drawFillRect(x, y, 1, 1, 0xff0f0f0f);
                else
                    r.drawFillRect(x, y, 1, 1, 0x00f9f9f9);
            }
        }
*/
        if (fuel > 0) {
            r.setzDepth(1);
            if (fuel < maxFuel)
                r.drawFuel(fuel, maxFuel, 0xffffff00);
            else
                r.drawFuel(fuel, maxFuel, 0xff00ff00);
        }
        for (GameObject obj : objects)
            obj.render(gc, r);
        // r.drawLight(light, Main.width/2, Main.height/2);
        //r.drawLight(light, gc.getInput().getMouseX(), gc.getInput().getMouseY());

    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public GameObject getObject(String tag) {
        for (int a = 0; a < objects.size(); a++) {
            if (objects.get(a).getTag().equals(tag)) {
                return objects.get(a);
            }
        }
        return null;
    }

    public boolean getCollision(int x, int y) {
        if (x < 0 || x >= levelW || y < 0 || y >= levelH)
            return true;
        return collision[x + y * levelW];
    }

    public void setCollision(int x, int y, boolean value) {
        collision[x + y * Main.width] = value;
    }

    public Image getBackgorund() {
        return backgorund;
    }

    public void setBackgorund(Image backgorund) {
        this.backgorund = backgorund;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public WallLogic getWallLogic() {
        return wallLogic;
    }

    public void addScore(int score) {
        player.addScore((int) (score * speed));
    }

    public Player getPlayer() {
        return player;
    }

    public void addFuel(float value) {
        fuel += value;
        if (fuel > maxFuel)
            fuel = maxFuel;
        System.out.println("V: " + value + " F: " + fuel);
    }
}
