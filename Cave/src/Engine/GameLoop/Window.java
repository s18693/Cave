package Engine.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

//import java.awt.image.VolatileImage;

public class Window {
    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;

    public Window() {
        this.image = new BufferedImage(Main.width, Main.height, BufferedImage.TYPE_INT_RGB);
        this.canvas = new Canvas();
        Dimension s = new Dimension((int) (Main.width * Main.scale), (int) (Main.height * Main.scale));
        canvas.setPreferredSize(s);
        canvas.setMaximumSize(s);
        canvas.setMinimumSize(s);

        frame = new JFrame(Main.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();
    }

    public void update() {
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bs.show();
    }

    public BufferedImage getImage(){
        return image;
    }
    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void close(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
