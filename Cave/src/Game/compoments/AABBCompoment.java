package Game.compoments;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Game.GameMenager;
import Game.Physics;

public class AABBCompoment extends Compoment {
    private GameObject parent;
    private GameMenager gm;
    private int centerX, centerY, halfWidth, halfHeight;

    public AABBCompoment(GameObject parent, GameMenager gm) {
        this.parent = parent;
        this.gm = gm;
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        halfHeight = parent.getWidth() / 2;
        halfWidth = parent.getHeight() / 2;
        centerX = (int) (parent.getPosX() + halfWidth);
        centerY = (int) (parent.getPosY() + halfHeight);

        Physics.addAABBCompoment(this);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {

    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getHalfWidth() {
        return halfWidth;
    }

    public void setHalfWidth(int halfWidth) {
        this.halfWidth = halfWidth;
    }

    public int getHalfHeight() {
        return halfHeight;
    }

    public void setHalfHeight(int halfHeight) {
        this.halfHeight = halfHeight;
    }

    public GameObject getParent() {
        return parent;
    }

    public GameMenager getGameMenager(){
        return gm;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
