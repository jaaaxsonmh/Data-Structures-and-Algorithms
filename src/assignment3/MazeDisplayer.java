package assignment3;

/**
 *
 * @author jackh
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MazeDisplayer extends JPanel implements ActionListener, KeyListener {

    private Maze maze;
    private DrawPanel drawPanel;
    private JButton performMazeAlg, loadMazeButton, createDumbMouse, createSmartMouse, createKeyboardMouse, timeMazeCompletion;
    private Timer refresh, stopWatch;
    private List<Mouse> mouses = new ArrayList<>();
    private KeyboardMouse keyMouse;
    private Mouse game1, game2;
    protected JLabel console = new JLabel("Please build a maze", SwingConstants.CENTER);
    protected boolean mazeBuilt;
    private String winner;

    public MazeDisplayer() {
        super(new BorderLayout());
        addKeyListener(this);
        setFocusable(true);

        maze = new Maze(20, 20);

        console.setFont(new Font("Comic Sans", Font.BOLD, 24));

        refresh = new Timer(15, this);
        refresh.start();
        // create the mouses
        JPanel southPanel = new JPanel();
        loadMazeButton = new JButton("Load Maze from DB");
        loadMazeButton.addActionListener((a) -> maze = MazeMaker.loadMazeFromDatabase("enormous", "student", "fpn871"));
        loadMazeButton.setFocusable(false);
        southPanel.add(loadMazeButton);
        performMazeAlg = new JButton("Build Paths");
        performMazeAlg.addActionListener((a) -> {
            if (!mazeBuilt) {
                MazeMaker.createMazePathsInThread(maze);
                console.setText("Building maze");
                performMazeAlg.setText("New maze");
            } else {
                if(!mouses.isEmpty()) {
                    mouses.clear();
                }
                console.setText("Creating new maze");
                maze = new Maze(20, 20);
                MazeMaker.createMazePathsInThread(maze);
            }
            mazeBuilt = true;
        });
        performMazeAlg.setFocusable(false);
        southPanel.add(performMazeAlg);

        createDumbMouse = new JButton("Dumb mouse");
        createDumbMouse.addActionListener((a) -> {
            if (mazeBuilt) {
                Mouse mouse = new RandomMouse(maze, 100, 0, 0);
                new Thread(mouse).start();
                mouses.add(mouse);
                console.setText("dumb moused spawned! Current amount of mice: " + mouses.size());
            } else {
                console.setText("Can't load dumb mouse when no maze built");
            }

        });
        createDumbMouse.setFocusable(false);
        southPanel.add(createDumbMouse);

        createSmartMouse = new JButton("Smart Mouse");
        createSmartMouse.addActionListener((a) -> {
            if (mazeBuilt) {
                Mouse mouse = new SmartMouse(maze, 100, 0, 0);
                new Thread(mouse).start();
                mouses.add(mouse);
                console.setText("Smart moused spawned! Current amount of mice: " + mouses.size());
            } else {
                console.setText("Can't load smart mouse when no maze built");
            }
        });
        createSmartMouse.setFocusable(false);
        southPanel.add(createSmartMouse);

        createKeyboardMouse = new JButton("Keyboard Mouse");
        createKeyboardMouse.addActionListener((a) -> manageKeyboardMouse());
        createKeyboardMouse.setFocusable(false);
        southPanel.add(createKeyboardMouse);

        timeMazeCompletion = new JButton("Start timed race");
        timeMazeCompletion.addActionListener((a) -> {
            console.setText("Game is starting...");

            //clear all mouses, as could be any number of random/smarts
            if (!mouses.isEmpty()) {
                mouses.clear();
            }
            // create the mice for game
            game1 = new SmartMouse(maze, 150, 0, 0);
            game2 = new SmartMouse(maze, 150, 0, 0);
            keyMouse = new KeyboardMouse(maze, 100, 0, 0);
            new Thread(game1).start();
            new Thread(game2).start();
            new Thread(keyMouse).start();
            mouses.add(keyMouse);
            mouses.add(game1);
            mouses.add(game2);

            lastTickTime = System.currentTimeMillis();
            manageTimer();
        });
        timeMazeCompletion.setFocusable(false);
        southPanel.add(timeMazeCompletion);

        add(southPanel, BorderLayout.SOUTH);
        drawPanel = new DrawPanel();

        add(drawPanel, BorderLayout.CENTER);
        add(console, BorderLayout.NORTH);

//        mouses.forEach(m -> new Thread(m).start());
    }

    public void manageKeyboardMouse() {
        if (mazeBuilt) {
            createKeyboardMouse.setText("Restart");
            if (keyMouse != null) {
                mouses.remove(keyMouse);
                console.setText("Removing the current keyboard mouse");
            }
            keyMouse = new KeyboardMouse(maze, 100, 0, 0);
            new Thread(keyMouse).start();
            mouses.add(keyMouse);
            console.setText("keyboard mouse spawned! Current amount of mice: " + mouses.size());

        } else {
            console.setText("Can't load keyboard mouse when no maze built");
        }
    }

    private long lastTickTime;

    private void manageTimer() {
        stopWatch = new Timer(100, (ActionEvent e) -> {
            long runningTime = System.currentTimeMillis() - lastTickTime;
            Duration duration = Duration.ofMillis(runningTime);
            duration = duration.minusHours(duration.toHours());
            long minutes = duration.toMinutes();
            duration = duration.minusMinutes(minutes);
            long ms = duration.toMillis();
            long seconds = ms / 1000;
            ms -= (seconds * 1000);
            String time = String.format("%02d minutes and %02d seconds", minutes, seconds);
            console.setText(time);

            if (keyMouse.getRequestStop()) {
                keyMouse.setWinner(true);
            } else if (game1.getRequestStop() || game2.getRequestStop()) {
                keyMouse.setWinner(false);
            }

            if (keyMouse.getRequestStop() || game1.getRequestStop() || game2.getRequestStop()) {
                String winner = keyMouse.getWinner() ? "You won" : "You lost";
                console.setText(winner + "! in: " + time);
                mouses.clear();
                stopWatch.stop();
            }
        });
        stopWatch.start();
        createKeyboardMouse.setText("Keyboard Mouse");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawPanel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke
    ) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_W) {
            keyMouse.setDirection(Direction.NORTH);
        }
        if (key == KeyEvent.VK_D) {
            keyMouse.setDirection(Direction.EAST);
        }
        if (key == KeyEvent.VK_S) {
            keyMouse.setDirection(Direction.SOUTH);
        }
        if (key == KeyEvent.VK_A) {
            keyMouse.setDirection(Direction.WEST);
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke
    ) {
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
            if (maze != null) {
                maze.drawMaze(g, getWidth() - 20, getHeight() - 20);
            }
            if (mouses != null) {
                mouses.forEach(m -> m.drawMouse(g, getWidth() - 20, getHeight() - 20));
            }
        }
    }

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
