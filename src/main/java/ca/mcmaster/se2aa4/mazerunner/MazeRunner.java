package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private Coordinate coords;
    private Direction heading;
    private Coordinate start_coords;
    private Coordinate end_coords;
    private MovementLogger path_logger;
    private Direction solving_direction;
    private EntranceFinder finder;

    public MazeRunner(Maze maze_in) throws EntranceException {
        /* Constructor, assumes solving direction west -> east. Can be switched using switchSides(); */

        // Initializing required objects, and vars.
        maze = maze_in;
        finder = new EntranceFinder(maze_in);
        path_logger = new MovementLogger();
        heading = Direction.EAST;
        solving_direction = Direction.EAST;
        
        // Getting start & end coordinates, and moving runner to start
        start_coords = new Coordinate(0,finder.findWestEntrance());
        logger.info("**** Entrance y cooridate: " + start_coords.y());

        end_coords = new Coordinate(maze.width() - 1, finder.findEastEntrance());
        logger.info("**** Exit y cooridate: " + end_coords.y());

        coords = new Coordinate(start_coords);
    }

    public void switchSides() {
        /* Switches solving direction (starts west -> starts east and vice versa) 
         * Does not reset path log, this must be done manually with reset(). */

        // Switching start and end coordinates
        Coordinate start_new = new Coordinate(end_coords);
        Coordinate end_new = new Coordinate(start_coords);
        start_coords = start_new;
        end_coords = end_new;

        // Moving runner to opposite starting position
        if (solving_direction == Direction.EAST) {
            heading = Direction.WEST;
            solving_direction = Direction.WEST;
        } else {
            heading = Direction.EAST;
            solving_direction = Direction.EAST;
        }

    }

    public void reset() {
        /* Resets runner to start and clears path log. Does not reset solving direction. */
        coords = new Coordinate(start_coords);
        path_logger.clear();

        if (solving_direction == Direction.EAST) {
            heading = Direction.EAST;
        } else {
            heading = Direction.WEST;
        }
    }

    public String canonicalPath() {
        /* Gets the path traveled by the runner in canonical form */
        return path_logger.getCanonical();
    }

    public String factorizedPath() {
        /* Gets the path traveled by the runner in factorized form */
        return path_logger.getFactorized();
    }

    public Direction heading() {
        /* Gets the current heading of the runner */
        return heading;
    }

    public Coordinate coords() {
        /* Gets the current coordinates of the runner */
        Coordinate temp_coord = new Coordinate(coords);
        return temp_coord;
    }

    public void moveForward() {
        /* Moves the runner forward one space. Also logs this action. 
         * Forward is relative to the direction the runner is currently facing.*/

        path_logger.forward();
        if (heading == Direction.NORTH) {
            coords.setY(coords.y() - 1);
        } else if (heading == Direction.SOUTH) {
            coords.setY(coords.y() + 1);
        } else if (heading == Direction.EAST) {
            coords.setX(coords.x() + 1);
        } else if (heading == Direction.WEST) {
            coords.setX(coords.x() - 1);
        }
    }

    public void turnRight() {
        /* Turns the runner to the right by 90 degrees. Also logs this action. 
         * Right is relative to the direction the runner is currently facing.*/

        path_logger.right();
        if (heading == Direction.NORTH) {
            heading = Direction.EAST;
        } else if (heading == Direction.SOUTH) {
            heading = Direction.WEST;
        } else if (heading == Direction.EAST) {
            heading = Direction.SOUTH;
        } else if (heading == Direction.WEST) {
            heading = Direction.NORTH;
        }
    }

    public void turnLeft() {
        /* Turns the runner to the left by 90 degrees. Also logs this action. 
         * Left is relative to the direction the runner is currently facing.*/

        path_logger.left();
        if (heading == Direction.NORTH) {
            heading = Direction.WEST;
        } else if (heading == Direction.SOUTH) {
            heading = Direction.EAST;
        } else if (heading == Direction.EAST) {
            heading = Direction.NORTH;
        } else if (heading == Direction.WEST) {
            heading = Direction.SOUTH;
        }
    }

    public boolean reachedExit() {
        /* Returns true if runner is standing on the exit tile. Returns false otherwise. */

        if (coords.x() == end_coords.x() & coords.y() == end_coords.y()) {
            return true;
        } else {
            return false;
        }
    }
}

enum Direction {
    NORTH, SOUTH, EAST, WEST
}
