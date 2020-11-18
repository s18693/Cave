package Game.objects.gameObject;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Main;
import Engine.GameLoop.Renderer;
import Game.GameMenager;
import Game.compoments.GameObject;
import Game.objects.gameObject.Enemy.Enemy;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameLogic extends GameObject {

    //ArrayList<GameObject> objects = new ArrayList<GameObject>();
    private float speed = 0, maxSpeed = 6, minSpeed = 1, speedBonus = 0.01f;
    private WallLogic wallLogic;


    public GameLogic(GameMenager gm) {
        this.tag = "GameLogic";
        //objects.add(wallLogic);
        //objects.add(new Enemy(Main.width,Main.height/2,50,3,100));
        //objects.add(new Platform());
    }


    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        //for (GameObject go : objects)
         //   go.update(gc, gm, dt);

        if(gm.getObject("enemy") == null) {
            int armor = gm.level + (int)(Math.random()*gm.level);
            gm.addObject(new Enemy(gm,Main.width + 100, gm.getWallLogic().getTopWall() + (gm.getWallLogic().getBotWall()-gm.getWallLogic().getTopWall())/2, 250, 100, armor, 1, 100));
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        //for (GameObject go : objects)
        //    go.render(gc, r);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {
        /*
        for (GameObject go : objects)
            go.collision(other);
            */
    }
}
