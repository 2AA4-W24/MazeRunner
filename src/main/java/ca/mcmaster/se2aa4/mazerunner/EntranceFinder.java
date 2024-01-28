package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class EntranceFinder {

    private Maze maze;

    public EntranceFinder(Maze maze_in) {
        /* Constructor */
        maze = maze_in;
    }

    public int findWestEntrance() throws EntranceException {
        /* Finds maze west entrance, assumes only one west entrance */

        ArrayList<Tile> entry_column = maze.getColumn(0);
        for (int i = 0; i < entry_column.size(); i++) {
            if(entry_column.get(i) == Tile.PASS) {
                return i;
            }
        }
        throw new EntranceException("Unable to find west entrance");
    }

    public int findEastEntrance() throws EntranceException {
        /* Finds maze east entrance, assumes only one east entrance */
        
        ArrayList<Tile> entry_column = maze.getColumn(maze.width() - 1);
        for (int i = 0; i < entry_column.size(); i++) {
            if(entry_column.get(i) == Tile.PASS) {
                return i;
            }
        }
        throw new EntranceException("Unable to find east entrance");
    }
}
