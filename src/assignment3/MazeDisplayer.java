package src.assignment3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MazeDisplayer extends JPanel implements ActionListener {
    private Maze maze;
    private DrawPanel drawPanel;
    private JButton performMazeAlg;
    private JButton loadMazeButton;
    private Timer timer;

    public MazeDisplayer() {
        super(new BorderLayout());
        maze = new Maze(10, 10);

        timer = new Timer(15, this);
        timer.start();
        // create the mouses
        JPanel southPanel = new JPanel();
        loadMazeButton = new JButton("Load Maze from DB");
        loadMazeButton.addActionListener((a) -> maze = MazeMaker.loadMazeFromDatabase("enormous", "student", "fpn871"));
        southPanel.add(loadMazeButton);
        performMazeAlg = new JButton("Build Paths");
        performMazeAlg.addActionListener((a) -> MazeMaker.createMazePathsInThread(maze));

        southPanel.add(performMazeAlg);

        add(southPanel, BorderLayout.SOUTH);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
    }

    private class DrawPanel extends JPanel {
        public DrawPanel() {
            super();
            super.setBackground(Color.WHITE);
            super.setPreferredSize(new Dimension(600, 600));
        }

        // draws the maze and draws the different mouses in the maze
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (maze != null)
                maze.drawMaze(g, getWidth() - 20, getHeight() - 20);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        drawPanel.repaint();
    }
    // request keyboard focus

    // main method to test this game
    public static void main(String[] args) {

        JFrame frame = new JFrame("Maze Maker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MazeDisplayer());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2, (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
    }
}
