package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class Maze {

    private ArrayList<ArrayList<Point>> maze = new ArrayList<ArrayList<Point>>();
    private int maze_width = 0;

    public void addLine(Point[] line_in) {

        if (maze_width == 0) {
            maze_width = line_in.length;
        } else if (maze_width != line_in.length){
            throw new IllegalArgumentException("Input length of " + line_in.length + " illegal for maze of width " + maze_width);
        }

        ArrayList<Point> maze_line = new ArrayList<Point>();

        for (int i = 0; i < line_in.length; i++) {
            maze_line.add(line_in[i]);
        }
        maze.add(maze_line);
    }

    public Point getPoint(int x_coord, int y_coord) {
        return maze.get(y_coord).get(x_coord);
    }

    public ArrayList<Point> getLine(int line_number) {
        return maze.get(line_number);
    }

    public ArrayList<Point> getColumn(int column_number) {

        ArrayList<Point> maze_column = new ArrayList<Point>();

        for (int i = 0; i < maze_width; i++) {
            maze_column.add(maze.get(i).get(column_number));
        }

        return maze_column;
    }
}

enum Point {
    PASS, WALL
}