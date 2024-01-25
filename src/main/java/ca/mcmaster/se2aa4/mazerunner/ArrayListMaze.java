package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class ArrayListMaze implements Maze {

    private ArrayList<ArrayList<Tile>> maze = new ArrayList<ArrayList<Tile>>();
    private int maze_width = 0;
    private int maze_height = 0;

    public void addLine(ArrayList<Tile> line_in) {

        if (maze_width == 0) {
            maze_width = line_in.size();
        } else if (maze_width != line_in.size()){
            throw new IllegalArgumentException("Input length of " + line_in.size() + " illegal for maze of width " + maze_width);
        }

        ArrayList<Tile> line = new ArrayList<Tile>();
        for (Tile point : line_in) {
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

    public Tile getPoint(Coordinate point) {
        return maze.get(point.y()).get(point.x());
    }

    public ArrayList<Tile> getLine(int line_number) {
        return maze.get(line_number);
    }

    public ArrayList<Tile> getColumn(int column_number) {

        ArrayList<Tile> maze_column = new ArrayList<Tile>();

        for (int i = 0; i < maze_width; i++) {
            maze_column.add(maze.get(i).get(column_number));
        }

        return maze_column;
    }
}

enum Tile {
    PASS, WALL
}