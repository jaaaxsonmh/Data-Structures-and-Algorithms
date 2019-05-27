package assignment3;

/**
 * A class that builds paths in a maze (with an exit on far left east wall, also
 * can be run in a thread for animated path building.. Need to ensure both Maze,
 * Direction and Room done properly.
 */
import java.sql.*;
import java.util.*;

/**
 *
 * @author jackh
 * @auther seth hall
 */
public class MazeMaker {

    private static Random generator = new Random();
    private static int delay = 0;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://raptor2.aut.ac.nz:3306/mazes";

    public static void createMazePathsInThread(Maze maze) {
        delay = 5;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int numRows = maze.getNumRows();
                int numCols = maze.getNumCols();
                int startRow = generator.nextInt(numRows);
                visitRoom(maze, startRow, 0);
                // randomly open one door along the eastern wall of maze
                int exitRow = generator.nextInt(numRows);
                maze.openDoor(exitRow, numCols - 1, Direction.EAST);
            }
        });
        t.start();
    }

    public static Maze loadMazeFromDatabase(String mazeName, String username, String password) {
        Maze maze = null;
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, username, password);
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.print("JDBC Driver not found!");
        }
        // TODO THIS FOR QUESTION 3!!!!

        List<String> columns = new ArrayList<String>();
        try {
            ResultSet rs = connection.prepareStatement("SELECT * FROM `tiny`").executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int cols = md.getColumnCount();
            for (int i = 1; i <= cols; i++) {
                System.out.println(md.getColumnName(i));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        columns.forEach(System.out::println);

        try {
            ResultSet rs = connection.prepareStatement("SELECT MAX(`Col`) AS `Col`, MAX(`Row`) as `Row` FROM `" + mazeName + "`").executeQuery();
            int col = 0;
            int row = 0;
            while (rs.next()) {
                col = Math.max(col, rs.getInt("Col") + 1);
                row = Math.max(row, rs.getInt("Row") + 1);
            }
            rs.close();

            System.out.println("Size: " + col + ":" + row);

            maze = new Maze(row, col);
            rs = connection.prepareStatement("SELECT * FROM `" + mazeName + "`").executeQuery();
            while (rs.next()) {
                col = rs.getInt("Col");
                row = rs.getInt("Row");
                System.out.println(col + ":" + row);
                if (rs.getBoolean("North")) {
                    maze.openDoor(row, col, Direction.NORTH);
                }
                if (rs.getBoolean("East")) {
                    maze.openDoor(row, col, Direction.EAST);
                }
                if (rs.getBoolean("South")) {
                    maze.openDoor(row, col, Direction.SOUTH);
                }
                if (rs.getBoolean("West")) {
                    maze.openDoor(row, col, Direction.WEST);
                }
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maze;
    }

    // prepares a maze of the specified direction that hsa a single
    // exit somewhere along the eastern wall
    public static void createMazePaths(Maze maze) { // prepare a maze whose doors are all initially closed
        delay = 0;
        int numRows = maze.getNumRows();
        int numCols = maze.getNumCols();
        int startRow = generator.nextInt(numRows);
        visitRoom(maze, startRow, 0);
        // randomly open one door along the eastern wall of maze
        int exitRow = generator.nextInt(numRows);
        maze.openDoor(exitRow, numCols - 1, Direction.EAST);
    }

    // recursive helper method which uses a depth first search of maze
    // opening doors as it moves from room to room
    private static void visitRoom(Maze maze, int row, int col) { // randomize the order in which directions will be moved
        List<Direction> directionList = new ArrayList<Direction>(4);
        for (Direction direction : Direction.values()) {
            directionList.add(direction);
        }
        Collections.shuffle(directionList);
        Iterator<Direction> iterator = directionList.iterator();
        while (iterator.hasNext()) {
            Direction direction = iterator.next();
            // determine row and column of adjacent room
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

            // determine whether the adjacent room should be visited
            if (maze.isInsideMaze(adjRow, adjCol) && !maze.hasOpenDoor(adjRow, adjCol)) {
                maze.openDoor(row, col, direction);
                // Open opposite door
                int oposit = (direction.ordinal() + 2) % 4;
                Direction op = Direction.values()[oposit];
                maze.openDoor(adjRow, adjCol, op);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                }

                visitRoom(maze, adjRow, adjCol);
            }
        }
    }
}
