package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private Coordinate coords;
    private Heading heading;
    private Coordinate start_coords;
    private Coordinate end_coords;
    private MovementLogger path_logger;
    private Direction solving_direction;
    private EntranceFinder finder;

    public MazeRunner(Maze maze_in) throws EntranceException {
        maze = maze_in;
        finder = new EntranceFinder(maze_in);
        path_logger = new MovementLogger();
        heading = Heading.RIGHT;
        
        start_coords = new Coordinate(0,finder.findWestEntrance());
        logger.info("**** Entrance y cooridate: " + start_coords.y());

        end_coords = new Coordinate(maze.width() - 1, finder.findEastEntrance());
        logger.info("**** Exit y cooridate: " + end_coords.y());

        coords = new Coordinate(start_coords);
        solving_direction = Direction.EAST;
    }

    public void switchSides() {
        Coordinate start_new = new Coordinate(end_coords);
        Coordinate end_new = new Coordinate(start_coords);
        start_coords = start_new;
        end_coords = end_new;

        if (solving_direction == Direction.EAST) {
            heading = Heading.LEFT;
            solving_direction = Direction.WEST;
        } else {
            heading = Heading.RIGHT;
            solving_direction = Direction.EAST;
        }

    }

    public void reset() {
        coords = new Coordinate(start_coords);
        path_logger.clear();

        if (solving_direction == Direction.EAST) {
            heading = Heading.RIGHT;
        } else {
            heading = Heading.LEFT;
        }
    }

    public String canonicalPath() {
        return path_logger.getCanonical();
    }

    public String factorizedPath() {
        return path_logger.getFactorized();
    }

    public Heading heading() {
        return heading;
    }

    public Coordinate coords() {
        Coordinate temp_coord = new Coordinate(coords);
        return temp_coord;
    }

    public void moveForward() {
        path_logger.forward();
        if (heading == Heading.UP) {
            coords.setY(coords.y() - 1);
        } else if (heading == Heading.DOWN) {
            coords.setY(coords.y() + 1);
        } else if (heading == Heading.RIGHT) {
            coords.setX(coords.x() + 1);
        } else if (heading == Heading.LEFT) {
            coords.setX(coords.x() - 1);
        }
    }

    public void turnRight() {
        path_logger.right();
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
        path_logger.left();
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

    public boolean reachedExit() {
        if (coords.x() == end_coords.x() & coords.y() == end_coords.y()) {
            return true;
        } else {
            return false;
        }
    }
}

enum Heading {
    UP, DOWN, LEFT, RIGHT
}

enum Direction {
    EAST, WEST
}
