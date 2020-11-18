package Game.compoments;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Game.GameMenager;

public abstract class Compoment {
    protected String tag;

    public abstract void update(GameContainer gc, GameMenager gm, float dt);

    public abstract void render(GameContainer gc, Renderer r);

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
