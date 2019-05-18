package src.assignment3;

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
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                room[i][j] = new Room();
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

    public boolean hasOpenDoor(int row, int col) {
        return room[row][col].hasOpenDoor();
    }

    public void openDoor(int row, int col, Direction door) {
        room[row][col].openDoor(door);
    }

    public boolean isInsideMaze(int row, int col) {
        return row >= 0 && col >= 0 && row < numRows && col < numCols;
    }

    public void drawMaze(Graphics g, int worldWidth, int worldHeight) {
        int addX = worldWidth / numCols;
        int addY = worldHeight / numRows;
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {

                int startX = 5 + x * addX;
                int endX = startX + addX;
                int startY = 5 + y * addY;
                int endY = startY + addY;

                Room room = this.room[y][x];
                String opened = "";
                if (!room.isDoorOpen(Direction.NORTH)) {
                    g.drawLine(startX, startY - 1, endX, startY - 1);
                    g.drawLine(startX, startY, endX, startY);
                    g.drawLine(startX, startY + 1, endX, startY + 1);
                    opened += " north";
                }
                if (!room.isDoorOpen(Direction.SOUTH)) {
                    g.drawLine(startX, endY - 1, endX, endY - 1);
                    g.drawLine(startX, endY, endX, endY);
                    g.drawLine(startX, endY + 1, endX, endY + 1);
                    opened += " south";
                }

                if (!room.isDoorOpen(Direction.WEST)) {
                    g.drawLine(startX - 1, startY, startX - 1, endY);
                    g.drawLine(startX, startY, startX, endY);
                    g.drawLine(startX + 1, startY, startX + 1, endY);
                    opened += "\nwest";
                }

                if (!room.isDoorOpen(Direction.EAST)) {
                    g.drawLine(endX - 1, startY, endX - 1, endY);
                    g.drawLine(endX, startY, endX, endY);
                    g.drawLine(endX + 1, startY, endX + 1, endY);
                    opened += "\neast";
                }
                // g.drawString(opened, startX, startY + 10);
                // g.drawString(x + ":" + y, startX, startY + 20);
            }
        }
    }


}
