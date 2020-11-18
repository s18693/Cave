package Engine.GameLoop;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    private GameContainer gc;

    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 256;
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX, mouseY, scroll;

    public Input(GameContainer gc) {
        this.gc = gc;
        mouseX = 0;
        mouseY = 0;
        scroll = 0;

        gc.getWindow().getCanvas().addKeyListener(this);
        gc.getWindow().getCanvas().addMouseListener(this);
        gc.getWindow().getCanvas().addMouseMotionListener(this);
        gc.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update() {
        for (int a = 0; a < NUM_KEYS; a++) {
            keysLast[a] = keys[a];
        }
        for (int a = 0; a < NUM_BUTTONS; a++) {
            buttonsLast[a] = buttons[a];
        }
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean isButton(int keyCode) {
        return buttons[keyCode];
    }

    public boolean isButtonUp(int keyCode) {
        return !buttons[keyCode] && buttonsLast[keyCode];
    }

    public boolean isButtonDown(int keyCode) {
        return buttons[keyCode] && !buttonsLast[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int) (e.getX() / Main.scale);
        mouseY = (int) (e.getY() / Main.scale);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX() / Main.scale);
        mouseY = (int) (e.getY() / Main.scale);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }


}
