package Game.objects.gameObject.Enemy.Drops;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;

public class Drop extends GameObject {

    Image image;

    public Drop(GameMenager gm, int posX, int posY, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.height = 16;
        this.width = 16;
        this.image = image;
        this.addCompoment(new AABBCompoment(this, gm));
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        for (int a = 0; a < height; a++) {
            if(!gm.getCollision((int) posX, (int) posY + a))
                posX -= (150 * dt + gm.speed)/height;
            if (gm.getCollision((int) posX + width, (int) posY + a))
                posX -= 1;
            if (!gm.getCollision((int) posX + a, (int) posY + height)) {
                posY += 10 * dt;
            }
        }
        this.updateCompoments(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(image, (int) posX, (int) posY);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {

    }
}
