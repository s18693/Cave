package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;
import Game.compoments.Vector2D;

public class Bullet extends GameObject {
    private Vector2D direction;
    private Image IBullet;

    public Bullet(GameMenager gm, float posX, float posY, Vector2D direction, String tag) {
        this.tag = tag;
        this.posX = posX;
        this.posY = posY;
        this.height = 16;
        this.width = 16;
        this.direction = direction;
        this.IBullet = new Image("/Player/bullet.png");
        this.addCompoment(new AABBCompoment(this, gm));
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        posX += direction.x * direction.power * dt;
        posY += direction.y * direction.power * dt;

        if (gm.getCollision((int) posX, (int) posY) || gm.getCollision((int) posX + width, (int) posY) || gm.getCollision((int) posX, (int) posY + height) || gm.getCollision((int) posX + width, (int) posY + height)) {
            setDead(true);
        }
        this.updateCompoments(gc, gm, dt);

    }

    @Override
    public void render(GameContainer gc, Renderer r) {

        r.drawImage(IBullet, (int) posX, (int) posY, IBullet.getQ(), direction.y, direction.x);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {
        if (tag.equalsIgnoreCase("playerBullet") && other.getTag().equalsIgnoreCase("enemy"))
            setDead(true);
        if (tag.equalsIgnoreCase("enemyBullet") && other.getTag().equalsIgnoreCase("player"))
            setDead(true);
    }
}
