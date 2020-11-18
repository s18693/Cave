package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Game.GameMenager;
import Game.compoments.GameObject;

public class Wall extends GameObject {
    private float speed;

    public Wall(float posX, float posY, float speed) {
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        posX -= dt * speed;
        if(posX < -16)
            setDead(true);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int) posX, (int) posY, 16, 16, 0xffff0000);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {

    }
}
