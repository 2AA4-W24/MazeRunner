package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgo implements MazeSolver{

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public String solveMaze(Maze maze) {
        /* Generates a path through the maze using the right-hand algorithm */

        try {
            MazeRunner runner = new MazeRunner(maze);

            // Continue until we have reached the exit tile
            while (!runner.reachedExit()) {

                /* Initializing the coordinates that will represent our surrounding coordinates, 
                 * allowing us to observe our surrounding tiles */
                Coordinate right_coord = new Coordinate(runner.coords());
                Coordinate forward_coord = new Coordinate(runner.coords());
                Coordinate left_coord = new Coordinate(runner.coords());
                Coordinate rear_coord = new Coordinate(runner.coords());

                // Depending on the direction we're facing, shift our surrounding coordinates to their corresponding locations
                // This is required as all the coordinates are initialized as our current coordinates
                if (runner.heading() == Direction.NORTH) {
                    right_coord.setX(right_coord.x() + 1);
                    forward_coord.setY(forward_coord.y() - 1);
                    left_coord.setX(left_coord.x() - 1);
                    rear_coord.setY(rear_coord.y() + 1);
                } else if (runner.heading() == Direction.SOUTH) {
                    right_coord.setX(right_coord.x() - 1);
                    forward_coord.setY(forward_coord.y() + 1);
                    left_coord.setX(left_coord.x() + 1);
                    rear_coord.setY(rear_coord.y() - 1);
                } else if (runner.heading() == Direction.EAST) {
                    right_coord.setY(right_coord.y() + 1);
                    forward_coord.setX(forward_coord.x() + 1);
                    left_coord.setY(left_coord.y() - 1);
                    rear_coord.setX(rear_coord.x() - 1);
                } else if (runner.heading() == Direction.WEST) {
                    right_coord.setY(right_coord.y() - 1);
                    forward_coord.setX(forward_coord.x() - 1);
                    left_coord.setY(left_coord.y() + 1);
                    rear_coord.setX(rear_coord.x() + 1);
                }
                
                // If we can go right, do that, otherwise go straight, if not possible, go left, if not possible, turn around.
                if (maze.getPoint(right_coord) == Tile.PASS) {
                    runner.turnRight();
                    runner.moveForward();
                } else if (maze.getPoint(forward_coord) == Tile.PASS) {
                    runner.moveForward();
                } else if (maze.getPoint(left_coord) == Tile.PASS) {
                    runner.turnLeft();
                    runner.moveForward();
                } else if (maze.getPoint(rear_coord) == Tile.PASS) {
                    runner.turnRight();
                    runner.turnRight();
                    runner.moveForward();
                }
            }
            return runner.factorizedPath();

        } catch (EntranceException e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }
}
