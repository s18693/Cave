package Engine.GameLoop;

public class GameContainer implements Runnable {

    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame ag;
    private int maxFps = 0;

    public double fpsAmount = 30.0D;
    private final double UPDATE_CAP = 1.0D / fpsAmount;

    private boolean running = false;

    public GameContainer(AbstractGame ag) {
        this.ag = ag;
    }

    public void start() {
        window = new Window();
        renderer = new Renderer(this);
        thread = new Thread(this);
        input = new Input(this);

        thread.run();
    }

    public void stop() {

    }

    public void run() {
        running = true;
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        ag.start(this);

        while (running) {
            //No limit of fps
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                //TODO: Update game


                ag.update(this, (float) UPDATE_CAP);

                input.update();

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    //FPS in console
                    /*
                    if (fps > maxFps) {
                        maxFps = fps;
                        System.out.println("FPS: " + maxFps);

                    }*/
                }


            }

            if (render) {
                //TODO: Render Game
                renderer.clean();
                ag.render(this, renderer);
                //renderer.drawText("AAABBBCCCDDD",0,0,0xff00ffff);
                renderer.proccess();
                renderer.drawFPS("FPS " + fps, 0, 0, 0xff00ffff);
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void dispose() {

    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
