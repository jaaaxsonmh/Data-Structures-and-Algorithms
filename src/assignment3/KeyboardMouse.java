package assignment3;

import java.awt.Color;

/**
 *
 * @author jackh
 */
public class KeyboardMouse extends Mouse {

    private Direction direction;

    public KeyboardMouse(Maze maze, int delay, int startRow, int startCol) {
        super(maze, delay, startRow, startCol);
        color = Color.GREEN;
    }

    @Override
    protected void move() {
        if (maze.isOpen(row, col, Direction.EAST) && !maze.isInsideMaze(row, col, Direction.EAST)) {
            requestStop();
            return;
        }
        if (direction != null) {
            int adjRow = row, adjCol = col;

            switch (direction) {
                case NORTH:
                    adjRow--;
                    break;
                case EAST:
                    adjCol++;
                    break;
                case SOUTH:
                    adjRow++;
                    break;
                case WEST:
                    adjCol--;
                    break;
            }

            if (maze.isOpen(row, col, direction)) {
                if (maze.isInsideMaze(adjRow, adjCol)) {
                    row = adjRow;
                    col = adjCol;
                }
            }
            direction = null;
        }

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
