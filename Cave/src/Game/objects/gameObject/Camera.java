package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Main;
import Engine.GameLoop.Renderer;
import Game.GameMenager;
import Game.compoments.GameObject;

public class Camera {
    private float offX, offY;
    private String targetTag;
    private GameObject target = null;
    private int speed;

    public Camera(int speed, String tag) {
        this.speed = speed;
        this.targetTag = tag;
    }

    public void update(GameContainer gc, GameMenager gm, float dt) {
        if (target == null) {
            target = gm.getObject(targetTag);
        }
        if (target == null)
            return;
        float targetX = (target.getPosX() + target.getWidth() / 2) - Main.width / 2;
        float targetY = (target.getPosY() + target.getHeight() / 2) - Main.height / 2;

        offX -= dt * (offX - targetX) * speed;
        offY -= dt * (offY - targetY) * speed;
    }

    public void render(Renderer r){
        r.setCamX((int)offX);
        r.setCamY((int)offY);
    }

    public String getTargetTag() {
        return targetTag;
    }

    public void setTargetTag(String targetTag) {
        this.targetTag = targetTag;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getOffX() {
        return offX;
    }

    public void setOffX(float offX) {
        this.offX = offX;
    }

    public float getOffY() {
        return offY;
    }

    public void setOffY(float offY) {
        this.offY = offY;
    }
}
