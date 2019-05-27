package assignment3;

/**
 *
 * @author jackh
 */
import java.awt.*;
import java.util.Random;

public class RandomMouse extends Mouse {

    private static Random random = new Random(System.currentTimeMillis());

    public RandomMouse(Maze maze, int delay, int startRow, int startCol) {
        super(maze, delay, startRow, startCol);
        color = Color.RED;
    }

    @Override
    protected void move() {
        while (true) {
            if (maze.isOpen(row, col, Direction.EAST) && !maze.isInsideMaze(row, col, Direction.EAST)) {
                requestStop();
                return;
            }
            Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
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
                    break;
                }
            }
        }
    }
}
