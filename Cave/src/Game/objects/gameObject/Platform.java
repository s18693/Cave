package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Main;
import Engine.GameLoop.Renderer;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;

public class Platform extends GameObject {
    private int[][] walls = new int[Main.height][Main.width];

    public Platform(GameMenager gm) {
        this.tag = "platform";
        this.width = 32;
        this.height = 32;
        this.posX = 110;
        this.posY = 110;

        for (int y = 0; y < Main.height; y++)
            for (int x = 0; x < Main.width; x++)
                walls[y][x] = -1;
        for (int y = 0; y < 16; y++)
            for (int x = 0; x < Main.width; x++)
                walls[y][x] = 0xffff0000;
        for (int y = 400; y < Main.height; y++)
            for (int x = 0; x < Main.width; x++)
                walls[y][x] = 0xffff0000;

        this.addCompoment(new AABBCompoment(this, gm));
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        this.updateCompoments(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {

        for (int y = 0; y < Main.height; y++)
            for (int x = 0; x < Main.width; x++) {
                //Move
                if (walls[y][x] != -1)
                    r.setPixel(x, y, walls[y][x]);
            }
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {
        if (other.getTag().equalsIgnoreCase("player"))
            System.out.println("Col");
    }
}
