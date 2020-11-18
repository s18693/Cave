package other.GUI5;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.drawLine(0,0,getWidth()-1,getHeight()-1);
                g.drawLine(getWidth()-1,0,0,getHeight()-1);

            }
        };
        JTextArea textArea = new JTextArea();
        textArea.setForeground(Color.RED);
        textArea.setFont(new Font("Dialog",Font.ITALIC,14));
        textArea.setText("Bumper To ZIOMAL");
        frame.setForeground(Color.BLUE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);

        panel.setPreferredSize(new Dimension(100, 100));
        frame.getContentPane().add(panel);
        frame.getContentPane().add(textArea);
    }
}
