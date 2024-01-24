package ca.mcmaster.se2aa4.mazerunner;

public class PathReverser {
	public static String reversePath(String path_forwards) {
		String path_reverse = "";
		for (int i = path_forwards.length() - 1; i >= 0; i--) {
			if (path_forwards.charAt(i) == 'R') {
				path_reverse = path_reverse + 'L';
			} else if (path_forwards.charAt(i) == 'L') {
				path_reverse = path_reverse + 'R';
			} else {
				path_reverse = path_reverse + path_forwards.charAt(i);
			}
		}
		return path_reverse;
	}
}
