package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private Coordinate coords;
    private Heading heading;
    private String path;
    private Coordinate start_coords;
    private Coordinate end_coords;

    public MazeRunner(Maze maze_in) throws EntranceException, ExitException {
        maze = maze_in;
        path = "";
        heading = Heading.RIGHT;
        start_coords = new Coordinate(0,findStart());
        logger.info("**** Entrance y cooridate: " + start_coords.y());
        end_coords = new Coordinate(maze.width() - 1, findEnd());
        logger.info("**** Exit y cooridate: " + end_coords.y());
        coords = new Coordinate(start_coords);
        logger.info("Coordinates Are: " + coords.toString());
    }

    private int findStart() throws EntranceException {
        ArrayList<Tile> entry_column = maze.getColumn(0);
        for (int i = 0; i < entry_column.size(); i++) {
            if(entry_column.get(i) == Tile.PASS) {
                return i;
            }
        }
        throw new EntranceException("Unable to find maze entrance");
    }

    private int findEnd() throws ExitException {
        ArrayList<Tile> entry_column = maze.getColumn(maze.width() - 1);
        for (int i = 0; i < entry_column.size(); i++) {
            if(entry_column.get(i) == Tile.PASS) {
                return i;
            }
        }
        throw new ExitException("Unable to find maze exit");
    }

    public Coordinate getEnd() {
        Coordinate end = new Coordinate(end_coords);
        return end;
    }

    public String path() {
        return new String(path);
    }

    public Heading heading() {
        return heading;
    }

    public Coordinate coords() {
        Coordinate temp_coord = new Coordinate(coords);
        return temp_coord;
    }

    public void moveForward() {
        path = path + "F";
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
