package Exercises;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BallGuiComplete extends JPanel implements ActionListener {

    private JButton addBall;
    private DrawPanel drawPanel;
    public ArrayList<Ball> balls = new ArrayList<Ball>();


    public BallGuiComplete() {
        super(new BorderLayout());

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addBall = new JButton("Add Ball");
        addBall.addActionListener(this);
        buttonPanel.add(addBall);
        add(buttonPanel, BorderLayout.SOUTH);

        // call actionPerformed method every 25 using Swing timer
        // you can use this to update your GUI
        Timer timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addBall) {

            Ball ball = new Ball(drawPanel.getWidth(), drawPanel.getHeight());
            balls.add(ball);
            Thread t = new Thread(ball);
            t.start();
        }
        drawPanel.repaint();  // this will invoke DrawPanel to redraw itself, (paintComponent will be called)
    }

    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            setPreferredSize(new Dimension(800, 800));
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (Ball b : balls) {
                b.startBall(g);
            }
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jacks better than []");
        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BallGuiComplete());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
        // now display something while the main thread is still alive
    }
}