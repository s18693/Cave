package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Main;
import Engine.GameLoop.Renderer;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;

import java.util.*;

public class WallLogic extends GameObject {

    private int X0, Y0, speed = 100, repits, moveSpeed;
    private int topLimit = 16, botLimit = 464, topWall = 16, botWall = 464, option = -1, topMoveDown, topRep, topMoveUp, botMoveUp, botRep, botMoveDown, size;
    private float tick = 0, move = 0;
    private int[][] walls = new int[Main.height][Main.width];

    public void setSpeed(int speed) {
        moveSpeed = speed;
        //System.out.println(moveSpeed);
    }

    public void addSpeed(int speed) {
        moveSpeed += speed;
        //System.out.println(moveSpeed);
    }

    public WallLogic(GameMenager gm, int x0, int y0, int size) {
        this.tag = "wallLogic";
        this.X0 = x0;
        this.Y0 = y0;
        this.posX = X0;
        this.posY = Y0;
        this.size = size;
        this.addCompoment(new AABBCompoment(this, gm));
        for (int y = 0; y < Main.height; y++)
            for (int x = 0; x < Main.width; x++)
                walls[y][x] = -1;
        for (int y = 0; y < topLimit; y++)
            for (int x = 0; x < Main.width; x++)
                walls[y][x] = 0xffff0000;
        for (int y = botLimit; y < Main.height; y++)
            for (int x = 0; x < Main.width; x++)
                walls[y][x] = 0xffff0000;
        losuj();
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        //move
        for (int y = 0; y < Main.height; y++)
            for (int x = 0; x < Main.width; x++) {
                if (x != Main.width - 1)
                    if (walls[y][x] != walls[y][x + 1]) {
                        walls[y][x] = walls[y][x + 1];
                        if (walls[y][x] == -1)
                            gm.setCollision(x, y, false);
                        else
                            gm.setCollision(x, y, true);
                    }
            }
        //random event
        tick += 0.1;
        if (tick > move - moveSpeed / 4) {
            eventTopDown();
            eventTopUp();
            eventBotUp();
            eventBotDown();

        }
        //move
        for (int a = 0; a < moveSpeed; a++)
            for (int y = 0; y < Main.height; y++)
                for (int x = 0; x < Main.width; x++) {
                    if (x != Main.width - 1)
                        if (walls[y][x] != walls[y][x + 1]) {
                            walls[y][x] = walls[y][x + 1];
                            if (walls[y][x] == -1)
                                gm.setCollision(x, y, false);
                            else
                                gm.setCollision(x, y, true);
                        }
                }


    }

    @Override
    public void render(GameContainer gc, Renderer r) {

        for (int y = 0; y < Main.height; y++)
            for (int x = 0; x < Main.width; x++) {
                if (walls[y][x] != -1){
                    r.setPixel(x, y, walls[y][x]);
            }}
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {

    }

    void losuj() {
        if (option == -1) {
            option = (int) (Math.random() * 3.9F);
            move = (float) (Math.random());
        }
        if (option == 0) {
            topRep = (int) ((Math.random() + 1) * 10.9f);
            topMoveDown = 5 + (int) ((Math.random() + 1) * ((botWall - topWall - size - 6) / 100));
            if (topWall + topRep * topMoveDown < botWall - size) {
                option = 0;
            } else {
                topRep = (int) ((Math.random() + 1) * 10.9f);
                topMoveUp = 5 + (int) ((Math.random() + 1) * ((topWall - topLimit - 6) / 100));
                if (topMoveDown == 5 && topMoveUp == 5) {
                    //System.out.println(":C");
                    botRep = (int) ((Math.random() + 1) * 10.9f);
                    botMoveDown = 5 + (int) ((Math.random() + 1) * ((botLimit - botWall - 6) / 100));
                    option = 3;
                } else
                    option = 1;
            }
        } else if (option == 1) {
            topRep = (int) ((Math.random() + 1) * 10.9f);
            topMoveUp = 5 + (int) ((Math.random() + 1) * ((topWall - topLimit - 6) / 100));
            if (topWall - topRep * topMoveUp > topLimit + 1) {
                option = 1;
            } else {
                topRep = (int) ((Math.random() + 1) * 10.9f);
                topMoveDown = 5 + (int) ((Math.random() + 1) * ((botWall - topWall - size - 6) / 100));
                if (topMoveDown == 5 && topMoveUp == 5) {
                    //System.out.println(":C");
                    botRep = (int) ((Math.random() + 1) * 10.9f);
                    botMoveDown = 5 + (int) ((Math.random() + 1) * ((botLimit - botWall - 6) / 100));
                    option = 3;
                } else
                    option = 0;
            }
        } else if (option == 2) {

            botRep = (int) ((Math.random() + 1) * 10.9f);
            botMoveUp = 5 + (int) ((Math.random() + 1) * ((botWall - topWall - size - 6) / 100));
            if (botWall - botMoveUp * botRep > topWall + size) {
                option = 2;
            } else {
                botRep = (int) ((Math.random() + 1) * 10.9f);
                botMoveDown = 5 + (int) ((Math.random() + 1) * ((botLimit - botWall - 6) / 100));
                if (botMoveUp == 5 && botMoveDown == 5) {
                    //System.out.println(":C");
                    topRep = (int) ((Math.random() + 1) * 10.9f);
                    topMoveUp = 5 + (int) ((Math.random() + 1) * ((topWall - topLimit - 6) / 100));
                    option = 1;
                }
                option = 3;
            }
        } else if (option == 3) {
            botRep = (int) ((Math.random() + 1) * 10.9f);
            botMoveDown = 5 + (int) ((Math.random() + 1) * ((botLimit - botWall - 6) / 100));
            if (botWall + botMoveDown * botRep < botLimit - 1) {
                option = 3;
            } else {
                botRep = (int) ((Math.random() + 1) * 10.9f);
                botMoveDown = (int) ((Math.random() + 1) * ((botLimit - botWall - 1) / 100));
                if (botMoveUp == 5 && botMoveDown == 5) {
                    //System.out.println(":C");
                    topRep = (int) ((Math.random() + 1) * 10.9f);
                    topMoveUp = 5 + (int) ((Math.random() + 1) * ((topWall - topLimit - 6) / 100));
                    option = 1;
                }
                option = 2;
            }
        }
        //System.out.println(option + " Tu: " + topMoveUp + " Td: " + topMoveDown + " topr " + topRep + " Bu: " + botMoveUp + " Bd: " + botMoveDown + " botr " + botRep);
    }

    public int getTopWall(){
        return topWall;
    }
    public int getBotWall(){
        return botWall;
    }

    void topMoveDown() {
        //System.out.println(topWall);
        if (botWall - topWall > size) {
            walls[topWall][X0 - 1] = 0xffff0000;
            topWall++;
        }
    }

    void topMoveUp() {
        if (topWall - 2 > topLimit) {
            walls[topWall - 1][X0 - 1] = -1;
            topWall--;
        }
    }

    void botMoveDown() {
        //System.out.println(topWall);
        if (botWall + 2 < botLimit) {
            walls[botWall][X0 - 1] = -1;
            botWall++;
        }
    }

    void botMoveUp() {
        if (botWall - size - 2 > topWall) {
            walls[botWall - 1][X0 - 1] = 0xffff0000;
            botWall--;
        }
    }

    void eventTopDown() {
        if (option == 0) {
            for (int a = 0; a < topMoveDown; a++)
                topMoveDown();
            repits++;
            if (repits == topRep) {
                repits = 0;
                option = -1;
                losuj();
            }
            //System.out.println(repits + "/" + topRep);
            tick = 0;
        }
    }

    void eventTopUp() {
        if (option == 1) {
            for (int a = 0; a < topMoveUp; a++)
                topMoveUp();
            repits++;
            if (repits == topRep) {
                repits = 0;
                option = -1;
                losuj();
            }
            tick = 0;
        }
    }

    void eventBotUp() {
        if (option == 2) {
            for (int a = 0; a < botMoveUp; a++)
                botMoveUp();
            repits++;
            if (repits == botRep) {
                repits = 0;
                option = -1;
                losuj();
            }
            tick = 0;
        }
    }

    void eventBotDown() {
        if (option == 3) {
            for (int a = 0; a < botMoveDown; a++)
                botMoveDown();
            repits++;
            if (repits == botRep) {
                repits = 0;
                option = -1;
                losuj();
            }
            tick = 0;
        }
    }
}
