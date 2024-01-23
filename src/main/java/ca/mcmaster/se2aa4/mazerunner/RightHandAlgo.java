package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgo implements MazeSolver{

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public String solveMaze(Maze maze) {
        try {
            MazeRunner runner = new MazeRunner(maze);
            logger.info(runner.coords().x() + "," + runner.coords().y());

            while (!runner.reachedExit()) {
                Coordinate right_coord = new Coordinate(runner.coords());
                Coordinate forward_coord = new Coordinate(runner.coords());
                Coordinate left_coord = new Coordinate(runner.coords());
                Coordinate rear_coord = new Coordinate(runner.coords());

                logger.info(runner.coords().x() + "," + runner.coords().y());

                if (runner.heading() == Heading.UP) {
                    logger.info("heading is up");
                    right_coord.setX(right_coord.x() + 1);
                    forward_coord.setY(forward_coord.y() - 1);
                    left_coord.setX(left_coord.x() - 1);
                    rear_coord.setY(rear_coord.y() + 1);
                } else if (runner.heading() == Heading.DOWN) {
                    logger.info("heading is down");
                    right_coord.setX(right_coord.x() - 1);
                    forward_coord.setY(forward_coord.y() + 1);
                    left_coord.setX(left_coord.x() + 1);
                    rear_coord.setY(rear_coord.y() - 1);
                } else if (runner.heading() == Heading.RIGHT) {
                    logger.info("heading is right");
                    right_coord.setY(right_coord.y() + 1);
                    forward_coord.setX(forward_coord.x() + 1);
                    left_coord.setY(left_coord.y() - 1);
                    rear_coord.setX(rear_coord.x() - 1);
                } else if (runner.heading() == Heading.LEFT) {
                    logger.info("heading is left");
                    right_coord.setY(right_coord.y() - 1);
                    forward_coord.setX(forward_coord.x() - 1);
                    left_coord.setY(left_coord.y() + 1);
                    rear_coord.setX(rear_coord.x() + 1);
                }

                logger.info("Switch Statement exited");
                logger.info(runner.coords().x() + "," + runner.coords().y());

                if (maze.getPoint(right_coord) == Tile.PASS) {
                    logger.info("Turning Right");
                    runner.turnRight();
                    runner.moveForward();
                } else if (maze.getPoint(forward_coord) == Tile.PASS) {
                    logger.info("Moving Forwards");
                    runner.moveForward();
                } else if (maze.getPoint(left_coord) == Tile.PASS) {
                    logger.info("Turning Left");
                    runner.turnLeft();
                    runner.moveForward();
                } else if (maze.getPoint(rear_coord) == Tile.PASS) {
                    logger.info("Turning Around");
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
            return runner.path();
        } catch (EntranceException | ExitException e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }
}
