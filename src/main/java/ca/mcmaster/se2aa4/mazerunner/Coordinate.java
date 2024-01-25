package ca.mcmaster.se2aa4.mazerunner;

public class Coordinate {
	private int x_coord;
	private int y_coord;

	public Coordinate() {
		/* Initializes Coodinate at default position */
		x_coord = 0;
		y_coord = 0;
	}

	public Coordinate(int x, int y) {
		/* Initializes Coordinate at a specified position*/
		x_coord = x;
		y_coord = y;
	}

	public Coordinate(Coordinate coordinate_copy) {
		/* Initializes Coordinate using values of passed coordinate object */
		x_coord = coordinate_copy.x();
		y_coord = coordinate_copy.y();
	}

	public String toString() {
		/* Returns coordinate values in string form (i.e. "3,4") */
		return String.valueOf(x_coord) + "," + String.valueOf(y_coord);
	}

	public void set(int x, int y) {
		/* Updates both coodinate values */
		x_coord = x;
		y_coord = y;
	}

	public void setX(int x) {
		/* Updates coordinate x value */
		x_coord = x;
	}

	public void setY(int y) {
		/* Updates coordinate y value */
		y_coord = y;
	}

	public int x() {
		/* Gets coordinate x value */
		return x_coord;
	}

	public int y() {
		/* Gets coordinate y value */
		return y_coord;
	}
}
