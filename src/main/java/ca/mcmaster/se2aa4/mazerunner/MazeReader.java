package ca.mcmaster.se2aa4.mazerunner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MazeReader {

    public String maze_file;

    public MazeReader(String maze_in) {
        /* Constructor */
        maze_file = maze_in;
    }

    public Maze readMaze() throws FileNotFoundException, IOException {
        /* Reads maze from file into Maze object*/

        // Initializing maze object, file readers, and other vars.
        Maze maze = new ArrayListMaze();
        BufferedReader read = new BufferedReader(new FileReader(maze_file));
        String line_in;
        ArrayList<Tile> line = new ArrayList<Tile>();

        // Reading into the maze object line by line
        while ((line_in = read.readLine()) != null) {

            line.clear();
            for (int i = 0; i < line_in.length(); i++) {
                if (line_in.charAt(i) == '#') {
                    line.add(Tile.WALL);
                } else if (line_in.charAt(i) == ' ') {
                    line.add(Tile.PASS);
                }
            }
            maze.addLine(line);
        }
        read.close();
        return maze;
    }

}
