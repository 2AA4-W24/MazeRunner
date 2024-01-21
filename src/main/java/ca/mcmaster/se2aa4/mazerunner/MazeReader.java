package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class MazeReader {

    public String maze_file;

    public MazeReader(String maze_in) {
        maze_file = maze_in;
    }

    public Maze readMaze() {
        Maze maze = new Maze();
        return maze;
    }

}
