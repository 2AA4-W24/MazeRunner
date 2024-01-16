package ca.mcmaster.se2aa4.mazerunner;

public class MazeReader {

    public String maze_file;

    public MazeReader(String maze_in) {
        maze_file = maze_in;
    }

    public Maze readMaze() {
        Maze maze = new Maze();
        maze.addLine("no maze yet");
        return maze;
    }

}
