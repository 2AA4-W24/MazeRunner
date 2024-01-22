package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeReader {

    public String maze_file;

    public MazeReader(String maze_in) {
        maze_file = maze_in;
    }

    public Maze readMaze() throws FileNotFoundException, IOException {
        Maze maze = new Maze();

        BufferedReader read = new BufferedReader(new FileReader(maze_file));
        String line_in;
        ArrayList<Point> line = new ArrayList<Point>();

        while ((line_in = read.readLine()) != null) {
            line.clear();

            for (int i = 0; i < line_in.length(); i++) {
                if (line_in.charAt(i) == '#') {
                    line.add(Point.WALL);
                } else if (line_in.charAt(i) == ' ') {
                    line.add(Point.PASS);
                }
            }
            maze.addLine(line);
        }
        read.close();

        for (int i = 0; i < 7; i++) {
            ArrayList<Point> maze_line = maze.getLine(i);
            for (Point point : maze_line) {
                System.out.print(point + " ");
            }
            System.out.println();
        }
        return maze;
    }

}
