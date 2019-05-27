package assignment3;

/**
 *
 * @author jackh
 */
import java.awt.*;
import java.util.*;
import java.util.List;

public class SmartMouse extends Mouse {

    private static Random random = new Random(System.currentTimeMillis());

    public SmartMouse(Maze maze, int delay, int startRow, int startCol) {
        super(maze, delay, startRow, startCol);
        color = Color.BLUE;
        room = new Room(startRow, startCol);
        visit.add(room);
    }

    private List<Room> visited = new ArrayList<>();
    private LinkedList<Room> visit = new LinkedList();
    private Room room;

    class Room {

        int row, col;

        public Room(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Room)) {
                return false;
            }
            Room r = (Room) o;
            return r.row == row && r.col == col;
        }
    }

    @Override
    protected void move() {
        if (maze.isOpen(row, col, Direction.EAST) && !maze.isInsideMaze(row, col, Direction.EAST)) {
            requestStop();
            return;
        }
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        boolean added = false;
        for (Direction direction : directions) {
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
                    Room room = new Room(adjRow, adjCol);
                    if (!visited.contains(room)) {

                        visit.add(0, room);
                        added = true;
                        break;
                    }
                }
            }
        }
        if (!added) {
            visit.poll();
        }
        visited.add(room = visit.peekFirst());
        row = room.row;
        col = room.col;
    }
}
