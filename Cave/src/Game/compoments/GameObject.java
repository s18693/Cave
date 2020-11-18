package Game.compoments;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;

import java.util.ArrayList;

public abstract class GameObject {

    protected String tag;
    protected float posX, posY;
    protected int width, height;
    protected boolean dead = false;
    protected Image imageMain;

    protected ArrayList<Compoment> compoments = new ArrayList<Compoment>();

    public abstract void update(GameContainer gc, GameMenager gm, float dt);

    public abstract void render(GameContainer gc, Renderer r);

    public abstract void collision(GameObject other, GameMenager gm);

    public void updateCompoments(GameContainer gc, GameMenager gm, float dt) {
        for (Compoment c : compoments)
            c.update(gc, gm, dt);
    }

    public void renderCompoments(GameContainer gc, Renderer r) {
        for (Compoment c : compoments)
            c.render(gc, r);
    }

    public void addCompoment(Compoment c) {
        compoments.add(c);
    }

    public void removeCompoment(String tag) {
        for (int a = 0; a < compoments.size(); a++) {
            if (compoments.get(a).getTag().equalsIgnoreCase(tag))
                compoments.remove(a);
        }
    }

    public Compoment findCompoment(String tag) {
        for (int a = 0; a < compoments.size(); a++) {
            if (compoments.get(a).getTag().equalsIgnoreCase(tag))
                return compoments.get(a);
        }
        return null;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

}
