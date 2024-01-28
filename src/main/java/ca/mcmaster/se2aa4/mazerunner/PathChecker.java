package ca.mcmaster.se2aa4.mazerunner;

public class PathChecker {

	private Maze maze;
	private MazeRunner runner;
	
	public PathChecker(Maze maze_in) {
		/* Constructor */
		maze = maze_in;
	}

	public boolean checkPath(String path) throws EntranceException {
		/* Checks the correctness of the input path, checks both solving directions */
		
		runner = new MazeRunner(maze);
		boolean correct_east = runPath(path, runner); // Checking east solving direction
		runner.switchSides(); 
		runner.reset();
		boolean correct_west = runPath(path, runner); // Checking west direction

		// If either of solving directions works, the path is valid
		if (correct_east | correct_west) {
			return true;
		} else {
			return false;
		}
	}

	private boolean runPath(String path, MazeRunner runner) {
		/* Runs a path in one direction, and returns the correcness of the path in that direction 
		 * Direction is determined by the solving direction of the passed runner object */

		// Performing each operation in the input path
		for (int i = 0; i < path.length(); i++) {
			switch (path.charAt(i)) {
				case 'F':
					runner.moveForward();
					// If we move onto a tile that is a wall, the path is incorrect
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

		// If we have not run into a wall, and have ended on the exit tile, the path is correct.
		if (runner.reachedExit()) {
			return true;
		} else {
			return false;
		}
	}
}
