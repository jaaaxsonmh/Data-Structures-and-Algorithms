package assignment3;

import java.awt.*;

/**
 *
 * @author jackh
 */
public abstract class Mouse implements Runnable {

    protected int row, col;
    protected Maze maze;
    protected boolean stopRequested;
    protected int delay;
    protected Color color;
    private boolean winner;

    public Mouse(Maze maze, int delay, int startRow, int startCol) {
        this.maze = maze;
        this.delay = delay;
        this.row = startRow;
        this.col = startCol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    protected abstract void move();

    public void run() {
        while (!stopRequested) {
            move();
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void requestStop() {
        stopRequested = true;
    }

    public boolean getRequestStop() {
        return stopRequested;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean getWinner() {
        return winner;
    }

    private int diameter = 10;
    private int radius = diameter / 2;

    public void drawMouse(Graphics g, int width, int height) {
        g.setColor(color);
        int numCols = maze.getNumCols();
        int numRows = maze.getNumRows();
        int addX = width / numCols;
        int addZ = height / numRows;

        int startx = 5 + col * addX;
        int startz = 5 + row * addZ;
        g.fillOval(startx + addX / 2 - radius, startz + addZ / 2 - radius, diameter, diameter);
    }

}
