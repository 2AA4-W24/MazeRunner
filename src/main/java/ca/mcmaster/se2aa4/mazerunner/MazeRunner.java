package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunner {

    private Maze maze;
    public int x_coord;
    public int y_coord;
    public String heading;
    public String path;
    public int start_coord;
    public int end_coord;

    public MazeRunner(Maze maze_in) {
        maze = maze_in;
        start_coord = findStart();
        end_coord = findEnd();
    }

    private int findStart() {
        return -1;
    }

    private int findEnd() {
        return -1;
    }

    public void moveForward() {

    }

    public void turnRight() {

    }

    public void turnLeft() {

    }

}
