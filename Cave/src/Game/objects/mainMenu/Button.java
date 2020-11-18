package Game.objects.mainMenu;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;
import Game.compoments.GameObject;

import java.awt.event.MouseEvent;

public class Button extends GameObject {
    private Image image;
    protected boolean isChoose = false, isMouse = false;

    public Button(float posX, float posY, Image image) {
        this.tag = "button";
        this.posX = posX;
        this.posY = posY;
        this.width = 163;
        this.height = 46;
        this.image = image;

    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        //r.drawImage(image, (int) posX, (int) posY, image.getQ(),90);
        r.drawImage(image, (int) posX, (int) posY);
        if (isChoose)
            r.drawRect((int) posX - 5, (int) posY - 5, image.getW() + 6, image.getH() + 6, 0xff000000, 4);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {

    }

    public boolean isMouse() {
        return isChoose;
    }
}
