package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public interface Maze {
    public void addLine(ArrayList<Tile> line_in);
    public int height();
    public int width();
    public Tile getPoint(Coordinates point);
    public ArrayList<Tile> getLine(int line_number);
    public ArrayList<Tile> getColumn(int column_number);
}
