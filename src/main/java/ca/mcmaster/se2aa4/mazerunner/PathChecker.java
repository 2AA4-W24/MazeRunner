package ca.mcmaster.se2aa4.mazerunner;

public class PathChecker {

	private Maze maze;
	private MazeRunner runner;
	
	public PathChecker(Maze maze_in) {
		maze = maze_in;
	}

	public boolean checkPath(String path) throws EntranceException {
		runner = new MazeRunner(maze);
		boolean correct_east = runPath(path, runner);
		runner.switchSides();
		runner.reset();
		boolean correct_west = runPath(path, runner);
		if (correct_east | correct_west) {
			return true;
		} else {
			return false;
		}
	}

	private boolean runPath(String path, MazeRunner runner) {
		for (int i = 0; i < path.length(); i++) {
			switch (path.charAt(i)) {
				case 'F':
					runner.moveForward();
					if (maze.getPoint(runner.coords()) == Tile.WALL) {return false;}
					break;
				case 'R':
					runner.turnRight();
					break;
				case 'L':
					runner.turnLeft();
					break;
				default:
					break;
			}
		}
		if (runner.reachedExit()) {
			return true;
		} else {
			return false;
		}
	}
}
