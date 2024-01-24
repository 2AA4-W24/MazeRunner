package ca.mcmaster.se2aa4.mazerunner;

public class PathChecker {

	private Maze maze;
	private MazeRunner runner;
	
	public PathChecker(Maze maze_in) {
		maze = maze_in;
	}

	public boolean checkPath(String path) throws EntranceException, ExitException {
		String reverse_path = PathReverser.reversePath(path);
		System.out.println(reverse_path);
		if (runPath(path) | runPath(reverse_path)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean runPath(String path) throws EntranceException, ExitException {
		runner = new MazeRunner(maze);
		for (int i = 0; i < path.length(); i++) {
			switch (path.charAt(i)) {
				case 'F':
					runner.moveForward();
					System.out.println(i + " - " + path.charAt(i) + " - " + "Moved Forward - " + runner.coords().toString());
					if (maze.getPoint(runner.coords()) == Tile.WALL) {return false;}
					break;
				case 'R':
					runner.turnRight();
					System.out.println(i + " - " + path.charAt(i) + " - " + "Turned Right - " + runner.coords().toString());
					break;
				case 'L':
					runner.turnLeft();
					System.out.println(i + " - " + path.charAt(i) + " - " + "Turned Left - " + runner.coords().toString());
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
