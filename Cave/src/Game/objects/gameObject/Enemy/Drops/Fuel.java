package Game.objects.gameObject.Enemy.Drops;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;

public class Fuel extends Drop {

    public Fuel(GameMenager gm, int posX, int posY) {
        super(gm, posX, posY, new Image("/Icons/Drops/fuel.png"));
        this.tag = "fuel";
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        super.update(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        super.render(gc, r);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {

    }
}
