package ca.mcmaster.se2aa4.mazerunner;

public class Coordinate {
	private int x_coord;
	private int y_coord;

	public void set(int x, int y) {
		x_coord = x;
		y_coord = y;
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
