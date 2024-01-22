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
            runner.moveForward();
            runner.moveForward();
            runner.moveForward();
            return runner.path();
        } catch (EntranceException | ExitException e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
        
    }
}
