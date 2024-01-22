package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class Maze {

    private ArrayList<ArrayList<Point>> maze = new ArrayList<ArrayList<Point>>();
    private int maze_width = 0;
    private int maze_height = 0;

    public void addLine(ArrayList<Point> line_in) {

        if (maze_width == 0) {
            maze_width = line_in.size();
        } else if (maze_width != line_in.size()){
            throw new IllegalArgumentException("Input length of " + line_in.size() + " illegal for maze of width " + maze_width);
        }

        ArrayList<Point> line = new ArrayList<Point>();
        for (Point point : line_in) {
            line.add(point);
        }
        maze.add(line);
        maze_height++;
    }

    public int height() {
        return maze_height;
    }

    public int width() {
        return maze_width;
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