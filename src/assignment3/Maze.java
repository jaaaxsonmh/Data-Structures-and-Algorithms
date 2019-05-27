package assignment3;

/**
 *
 * @author jackh
 */
import java.awt.*;

public class Maze {

    private int numRows;
    private int numCols;
    private Room[][] room;

    public Maze() {
        this(100, 100);
    }

    public Maze(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.room = new Room[numRows][numCols];
        for (int x = 0; x < numRows; x++) {
            for (int z = 0; z < numCols; z++) {
                room[x][z] = new Room();
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public boolean isOpen(int row, int col, Direction door) {
        return room[row][col].isDoorOpen(door);
    }

    public boolean isInsideMaze(int row, int col, Direction add) {
        int adjRow = row, adjCol = col;
        switch (add) {
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
        return isInsideMaze(adjRow, adjCol);
    }

    public boolean hasOpenDoor(int row, int col) {
        return room[row][col].hasOpenDoor();
    }

    public void openDoor(int row, int col, Direction door) {
        room[row][col].openDoor(door);
    }

    public void drawMaze(Graphics g, int worldwidth, int worldheight) {
        int addX = worldwidth / numCols;
        int addZ = worldheight / numRows;
        for (int x = 0; x < numCols; x++) {
            for (int z = 0; z < numRows; z++) {

                int startx = 5 + x * addX;
                int endx = startx + addX;

                int startz = 5 + z * addZ;
                int endz = startz + addZ;
                Room room = this.room[z][x];
                String opened = "";
                if (!room.isDoorOpen(Direction.NORTH)) {
                    g.drawLine(startx, startz - 1, endx, startz - 1);
                    g.drawLine(startx, startz, endx, startz);
                    g.drawLine(startx, startz + 1, endx, startz + 1);
                    opened += " north";
                }
                if (!room.isDoorOpen(Direction.SOUTH)) {
                    g.drawLine(startx, endz - 1, endx, endz - 1);
                    g.drawLine(startx, endz, endx, endz);
                    g.drawLine(startx, endz + 1, endx, endz + 1);
                    opened += " south";
                }

                if (!room.isDoorOpen(Direction.WEST)) {
                    g.drawLine(startx - 1, startz, startx - 1, endz);
                    g.drawLine(startx, startz, startx, endz);
                    g.drawLine(startx + 1, startz, startx + 1, endz);
                    opened += "\nwest";
                }

                if (!room.isDoorOpen(Direction.EAST)) {
                    g.drawLine(endx - 1, startz, endx - 1, endz);
                    g.drawLine(endx, startz, endx, endz);
                    g.drawLine(endx + 1, startz, endx + 1, endz);
                    opened += "\neast";
                }
                // g.drawString(opened, startx, startz + 10);
                // g.drawString(x + ":" + z, startx, startz + 20);
            }
        }
    }

    public boolean isInsideMaze(int row, int col) {
        return row >= 0 && col >= 0 && row < numRows && col < numCols;
    }
}
