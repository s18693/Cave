package Game.objects.mainMenu;

import Engine.Audio.SoundClip;
import Engine.GameLoop.GameContainer;
import Engine.GameLoop.Renderer;
import Engine.gfx.Image;
import Game.GameMenager;
import Game.compoments.GameObject;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuButtonLogic extends GameObject {
    Image[] bi = new Image[4];
    public Button[] buttons = new Button[5];
    public Button back;
    public int option = 0;
    public boolean isActive = true, game = false, highscore = false, hsp = false, isMouse;
    Image background, hs;
    int tick = 0;
    SoundClip BSound, enter;

    public MenuButtonLogic() {
        bi[0] = new Image("/Buttons/button_start.png");
        bi[1] = new Image("/Buttons/button_multiplayer.png");
        bi[2] = new Image("/Buttons/button_highscore.png");
        bi[3] = new Image("/Buttons/button_exit.png");
        background = new Image("/background2.png");
        hs = new Image("/Buttons/highscore.png");


        buttons[0] = new Button(240, 130, bi[0]);
        buttons[1] = new Button(240, 210, bi[1]);
        buttons[2] = new Button(240, 290, bi[2]);
        buttons[3] = new Button(240, 370, bi[3]);
        back = new Button(240, 370, bi[3]);
        buttons[4] = back;

        this.tag = "MenuButtonLogic";
        buttons[option].isChoose = true;
        BSound = new SoundClip("/Audio/buttons.wav");
        enter = new SoundClip("/Audio/enter.wav");
    }

    @Override
    public void update(GameContainer gc, GameMenager gm, float dt) {
        if (gc.getInput().isKeyDown(KeyEvent.VK_UP) && isActive) {
            BSound.play();
            buttons[option].isChoose = false;
            option--;
            if (option < 0)
                option = 3;
            buttons[option].isChoose = true;
        }
        //mouse chose button i main menu
        if (isActive)
            for (int a = 0; a < buttons.length - 1; a++) {
                if (gc.getInput().getMouseX() > buttons[a].getPosX() && gc.getInput().getMouseX() < buttons[a].getPosX() + buttons[a].getWidth() && gc.getInput().getMouseY() > buttons[a].getPosY() && gc.getInput().getMouseY() < buttons[a].getPosY() + buttons[a].getHeight()) {
                    if (!isMouse)
                        BSound.play();
                    isMouse = true;
                    buttons[option].isChoose = false;
                    option = a;
                    buttons[option].isChoose = true;
                    //mouse click
                    if (gc.getInput().isButtonDown(MouseEvent.BUTTON1)) {
                        pressButton(gc, gm);
                    }
                } else if (option == a)
                    isMouse = false;
            }
        //Only back button
        if (highscore)
            if (gc.getInput().getMouseX() > buttons[buttons.length - 1].getPosX() && gc.getInput().getMouseX() < buttons[buttons.length - 1].getPosX() + buttons[buttons.length - 1].getWidth() && gc.getInput().getMouseY() > buttons[buttons.length - 1].getPosY() && gc.getInput().getMouseY() < buttons[buttons.length - 1].getPosY() + buttons[buttons.length - 1].getHeight()) {
                if (!isMouse)
                    BSound.play();
                isMouse = true;
                buttons[buttons.length - 1].isChoose = true;
                //mouse click
                if (gc.getInput().isButtonDown(MouseEvent.BUTTON1)) {
                    enter.play();
                    isActive = true;
                    highscore = false;
                    tick = 0;
                }
            }

        if (gc.getInput().isKeyDown(KeyEvent.VK_DOWN) && isActive) {
            BSound.play();
            buttons[option].isChoose = false;
            option++;
            if (option > 3)
                option = 0;
            buttons[option].isChoose = true;
        }

        if (gc.getInput().isKeyDown(KeyEvent.VK_ENTER)) {
            pressButton(gc, gm);
        }
        if (game) {
            gm.setBackgorund(background);
            game = false;
        }
        if (hsp) {
            back.isChoose = true;
            hsp = false;
        }
        for (Button button : buttons)
            button.update(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        if (isActive)
            for (int a = 0; a < buttons.length - 1; a++)
                buttons[a].render(gc, r);
        if (highscore) {
            buttons[buttons.length - 1].render(gc, r);
            r.drawImage(hs, 240, 130);
            back.render(gc, r);
        }
    }

    @Override
    public void collision(GameObject other, GameMenager gm) {

    }

    public void pressButton(GameContainer gc, GameMenager gm) {
        enter.play();
        tick++;
        if (isActive) {
            if (option == 0) {
                isActive = false;
                game = true;
                buttons[0].setDead(true);
                buttons[1].setDead(true);
                buttons[2].setDead(true);
                buttons[3].setDead(true);
                setDead(true);
                if (back != null)
                    back.setDead(true);
                gm.setGame(true);
            }
            if (option == 1) {
                isActive = false;
                game = true;
                buttons[0].setDead(true);
                buttons[1].setDead(true);
                buttons[2].setDead(true);
                buttons[3].setDead(true);
                setDead(true);
                if (back != null)
                    back.setDead(true);
                gm.setGame(true);
            }
            if (option == 2) {
                isActive = false;
                hsp = true;
                highscore = true;
            }
            if (option == 3)
                gc.getWindow().close();
        }
        if (highscore && tick > 1) {
            //System.out.println(tick);
            tick++;
            //System.out.println(tick);
            isActive = true;
            highscore = false;
            if (tick > 2) {
                tick = 0;
            }
        }
    }
}
