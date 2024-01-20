package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlgo implements MazeSolver{
    
    @Override
    public String solveMaze(Maze maze) {
        MazeRunner runner = new MazeRunner(maze);
        runner.moveForward();
        runner.moveForward();
        runner.moveForward();
        runner.moveForward();
        return runner.getPath();
    }
}
