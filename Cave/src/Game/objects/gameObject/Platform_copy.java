package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;

public class Platform_copy extends GameObject {

    public Platform_copy(GameMenager gm) {
        this.tag = "platform";
        this.width = 32;
        this.height = 32;
        this.posX = 110;
        this.posY = 110;

        this.addCompoment(new AABBCompoment(this, gm));
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        this.updateCompoments(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        //r.drawFillRect((int) posX, (int) posY, width, height, 0xff0fff0f);
        r.setPixel(110, 110, 0xff00ff00);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {
        if (other.getTag().equalsIgnoreCase("player"))
            System.out.println("Col");
    }
}
