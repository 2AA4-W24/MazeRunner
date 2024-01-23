package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgo implements MazeSolver{

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    public String solveMaze(Maze maze) {
        try {
            MazeRunner runner = new MazeRunner(maze);
            runner.moveForward();
            while (!runner.reachedExit()) {
                switch (runner.heading()) {
                    case Heading.UP: {
                        Coordinate right_coord = runner.coords();
                        Coordinate forward_coord = runner.coords();
                        Coordinate left_coord = runner.coords();
                        Coordinate rear_coord = runner.coords();
                        right_coord.setX(right_coord.x() + 1);
                        forward_coord.setY(forward_coord.y() - 1);
                        left_coord.setX(left_coord.x() - 1);
                        rear_coord.setY(rear_coord.y() + 1);

                    }

                }
            }
            return runner.path();
        } catch (EntranceException | ExitException e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        
    }
}
