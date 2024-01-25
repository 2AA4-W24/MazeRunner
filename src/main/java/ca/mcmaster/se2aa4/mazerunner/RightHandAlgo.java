package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgo implements MazeSolver{

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public String solveMaze(Maze maze) {
        try {
            MazeRunner runner = new MazeRunner(maze);

            while (!runner.reachedExit()) {
                Coordinate right_coord = new Coordinate(runner.coords());
                Coordinate forward_coord = new Coordinate(runner.coords());
                Coordinate left_coord = new Coordinate(runner.coords());
                Coordinate rear_coord = new Coordinate(runner.coords());

                if (runner.heading() == Heading.UP) {
                    right_coord.setX(right_coord.x() + 1);
                    forward_coord.setY(forward_coord.y() - 1);
                    left_coord.setX(left_coord.x() - 1);
                    rear_coord.setY(rear_coord.y() + 1);
                } else if (runner.heading() == Heading.DOWN) {
                    right_coord.setX(right_coord.x() - 1);
                    forward_coord.setY(forward_coord.y() + 1);
                    left_coord.setX(left_coord.x() + 1);
                    rear_coord.setY(rear_coord.y() - 1);
                } else if (runner.heading() == Heading.RIGHT) {
                    right_coord.setY(right_coord.y() + 1);
                    forward_coord.setX(forward_coord.x() + 1);
                    left_coord.setY(left_coord.y() - 1);
                    rear_coord.setX(rear_coord.x() - 1);
                } else if (runner.heading() == Heading.LEFT) {
                    right_coord.setY(right_coord.y() - 1);
                    forward_coord.setX(forward_coord.x() - 1);
                    left_coord.setY(left_coord.y() + 1);
                    rear_coord.setX(rear_coord.x() + 1);
                }

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
                } else {
                    logger.error("Runner Trapped");
                    logger.error("Right Tile: " + right_coord.x() + "," + right_coord.y() + " - " + maze.getPoint(right_coord));
                    logger.error("Forward Tile: " + forward_coord.x() + "," + forward_coord.y() + " - " + maze.getPoint(forward_coord));
                    logger.error("Left Tile: " + left_coord.x() + "," + left_coord.y() + " - " + maze.getPoint(left_coord));
                    logger.error("Rear Tile: " + rear_coord.x() + "," + rear_coord.y() + " - " + maze.getPoint(rear_coord));
                    return ("Unable to solve - Runner Trapped");
                }
            }
            return runner.canonicalPath();

        } catch (EntranceException | ExitException e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }
}
