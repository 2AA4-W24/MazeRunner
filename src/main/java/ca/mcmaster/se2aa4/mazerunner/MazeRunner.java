package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private int x_coord;
    private int y_coord;
    private Heading heading;
    private String path;
    private int start_coord;
    private int end_coord;

    public MazeRunner(Maze maze_in) {
        maze = maze_in;
        path = "";
        start_coord = findStart();
        logger.info("Entrance y cooridate: " + start_coord);
        end_coord = findEnd();
        logger.info("Exit y cooridate: " + end_coord);
    }

    private int findStart() {
        return 2;
    }

    private int findEnd() {
        return 2;
    }

    public int getEnd() {
        return end_coord;
    }

    public String getPath() {
        return new String(path);
    }

    public Heading getHeading() {
        return heading;
    }

    public int xCoord() {
        return x_coord;
    }

    public int yCoord() {
        return y_coord;
    }

    public void moveForward() {
        path = path + "F";
        if (heading == Heading.UP) {
            y_coord++;
        } else if (heading == Heading.DOWN) {
            y_coord--;
        } else if (heading == Heading.RIGHT) {
            x_coord++;
        } else if (heading == Heading.LEFT) {
            x_coord--;
        }
    }

    public void turnRight() {
        path = path + "R";
        if (heading == Heading.UP) {
            heading = Heading.RIGHT;
        } else if (heading == Heading.DOWN) {
            heading = Heading.LEFT;
        } else if (heading == Heading.RIGHT) {
            heading = Heading.DOWN;
        } else if (heading == Heading.LEFT) {
            heading = Heading.UP;
        }
    }

    public void turnLeft() {
        path = path + "L";
        if (heading == Heading.UP) {
            heading = Heading.LEFT;
        } else if (heading == Heading.DOWN) {
            heading = Heading.RIGHT;
        } else if (heading == Heading.RIGHT) {
            heading = Heading.UP;
        } else if (heading == Heading.LEFT) {
            heading = Heading.DOWN;
        }
    }

}

enum Heading {
    UP, DOWN, LEFT, RIGHT
}
