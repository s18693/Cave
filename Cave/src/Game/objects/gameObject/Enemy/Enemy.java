package Game.objects.gameObject.Enemy;

import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;
import Game.compoments.AABBCompoment;
import Game.compoments.GameObject;
import Game.compoments.Vector2D;
import Game.objects.gameObject.Bullet;
import Game.objects.gameObject.Enemy.Drops.Armor;
import Game.objects.gameObject.Enemy.Drops.Fuel;
import Game.objects.gameObject.Player;

public class Enemy extends GameObject {

    protected int armor, shield, shieldMax;
    protected float fireRate, fireTick, speed, move, moveTick, damageTick, shieldRegeneration, shieldTick;
    private Image p0, p1, p2, IPlayer, IShield;
    protected double x, y, c, sinus, cosinus;
    protected boolean TakeDamage = true;

    public Enemy(GameMenager gm, int posX, int posY, float speed, float move, int armor, int shieldMax, float fireRate) {
        this.tag = "enemy";
        this.posX = posX;
        this.posY = posY;
        this.width = 16;
        this.height = 16;
        this.speed = speed;
        this.armor = armor;
        this.shieldRegeneration = 200;
        this.shieldTick = 0;
        this.shieldMax = shieldMax;
        this.shield = shieldMax;
        this.fireRate = fireRate;
        this.fireTick = fireRate / 2;
        this.move = move;
        this.moveTick = 0;
        this.IPlayer = new Image("/Player/player.png");
        this.p0 = new Image("/Player/animation/p0.png");
        this.p1 = new Image("/Player/animation/p1.png");
        this.p2 = new Image("/Player/animation/p2.png");
        this.IShield = new Image("/Player/shield.png");
        this.imageMain = IPlayer;
        this.addCompoment(new AABBCompoment(this, gm));
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        //move left
        for (int a = 0; a < height; a++) {
            if (!gm.getCollision((int) posX, (int) posY + a)) {
                posX -= (speed * dt - gm.speed) / height;
            }
        }

        //look at player
        x = gm.getPlayer().getPosX() - posX;
        y = gm.getPlayer().getPosY() - posY;
        c = (Math.sqrt(((y * y) + (x * x))));
        sinus = y / c;
        cosinus = x / c;
        //move up/down like player
        moveTick++;
        if (moveTick > move) {
            if (y > 10) {
                for (int a = 0; a < width; a++)
                    if (!gm.getCollision((int) posX + a, (int) posY))
                        posY += (speed * dt * 0.2F) / width;
            }
            if (y < 10) {
                for (int a = 0; a < width; a++)
                    if (!gm.getCollision((int) posX + a, (int) posY + height))
                        posY -= (speed * dt * 0.2F) / width;
            }
            if (moveTick > move * 2)
                moveTick = 0;
        }
        //fire
        fireTick++;
        if (fireTick > fireRate) {
            Vector2D vector2D = new Vector2D(cosinus, sinus, 500);
            gm.addObject(new Bullet(gm,(float) (posX + 10 * cosinus), (float) (posY + 10 * sinus), vector2D, "enemyBullet"));
            fireTick = 0;
        }
        //auto kill
        if (posX + width < 0)
            setDead(true);
        //collision
        for (int a = 0; a < height; a++) {
            if (gm.getCollision((int) posX + width, (int) posY + a)) {
                posX -= 1;
                if (!gm.getCollision((int) posX, (int) posY + height + 10))
                    posY += speed * dt;
                else if (!gm.getCollision((int) posX, (int) posY - 10))
                    posY -= speed * dt;
            }
        }
        //No multi damage - on colsion one bullet take full armor - 2 = short break
        if (!TakeDamage)
            damageTick++;
        if (damageTick > 2) {
            TakeDamage = true;
            damageTick = 0;
        }
//shield
        if (shield < shieldMax)
            shieldTick++;
        if (shieldTick >= shieldRegeneration) {
            shield++;
            shieldTick = 0;
        }

        this.updateCompoments(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(imageMain, (int) posX, (int) posY, imageMain.getQ(), sinus, cosinus);
        r.setzDepth(0);
        if (shield > 0)
            r.drawImage(IShield, (int) posX - width / 2, (int) posY - height / 2);
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {
        if (other.getTag().equalsIgnoreCase("playerBullet")) {
            //System.out.println("D");
            if (TakeDamage) {
                if (shield > 0)
                    shield--;
                else if (armor > 0)
                    armor--;
                else if (armor == 0 && shield == 0) {
                    gm.addScore(10);
                    int los = 1;//(int) (Math.random() * 20.9f);
                    if (los == 1)
                        gm.addObject(new Fuel(gm,(int) posX, (int) posY));
                    if (los == 15)
                        gm.addObject(new Armor(gm,(int) posX, (int) posY));

                    setDead(true);
                }
                TakeDamage = false;
            }
        }
    }
}
