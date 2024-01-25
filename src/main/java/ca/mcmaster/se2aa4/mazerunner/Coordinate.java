package ca.mcmaster.se2aa4.mazerunner;

public class Coordinate {
	private int x_coord;
	private int y_coord;

	public Coordinate() {
		x_coord = 0;
		y_coord = 0;
	}

	public Coordinate(int x, int y) {
		x_coord = x;
		y_coord = y;
	}

	public Coordinate(Coordinate coordinate_copy) {
		x_coord = coordinate_copy.x();
		y_coord = coordinate_copy.y();
	}

	public String toString() {
		return String.valueOf(x_coord) + "," + String.valueOf(y_coord);
	}

	public void set(int x, int y) {
		x_coord = x;
	}

	public void setX(int x) {
		x_coord = x;
	}

	public void setY(int y) {
		y_coord = y;
	}

	public int x() {
		return x_coord;
	}

	public int y() {
		return y_coord;
	}
}
