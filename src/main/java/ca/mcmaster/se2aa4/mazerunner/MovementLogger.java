package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class MovementLogger {

    /*  Path is stored as a 2d arraylist, with repeated actions being grouped into the same sub-list for ease of factorization
     * i.e. FFFRF -> [[F,F,F], [R], [F]] */
    private ArrayList<ArrayList<Character>> path = new ArrayList<ArrayList<Character>>();
    private Move last_move = Move.NONE;

    public void forward() {
        /* Logs a forward movement */

        // If this is a repeated movement, it is appended to the last sub-list, if not, a new sub-list is created
        if (last_move == Move.FORWARD) {
            path.getLast().add('F');
        } else {
            ArrayList<Character> temp_list = new ArrayList<Character>();
            temp_list.add('F');
            path.add(temp_list);
            last_move = Move.FORWARD;
        }
    }

    public void right() {
        /* Logs a right turn */

        // If this is a repeated movement, it is appended to the last sub-list, if not, a new sub-list is created
        if (last_move == Move.RIGHT) {
            path.getLast().add('R');
        } else {
            ArrayList<Character> temp_list = new ArrayList<Character>();
            temp_list.add('R');
            path.add(temp_list);
            last_move = Move.RIGHT;
        }
    }

    public void left() {
        /* Logs a left turn */

        // If this is a repeated movement, it is appended to the last sub-list, if not, a new sub-list is created
        if (last_move == Move.LEFT) {
            path.getLast().add('L');
        } else {
            ArrayList<Character> temp_list = new ArrayList<Character>();
            temp_list.add('L');
            path.add(temp_list);
            last_move = Move.LEFT;
        }
    }

    public void clear() {
        /* Clears the logged path */
        path.clear();
        last_move = Move.NONE;
    }

    public String getCanonical() {
        /* Returns the logged path in canonical form */    

        // Iterate through the path, adding spaces between different types of operations
        String canonical = "";
        for (ArrayList<Character> chain : path) {
            for (Character move : chain) {
                canonical = canonical + move;
            }
            canonical = canonical + ' ';
        }
        return canonical;
    }

    public String getFactorized() {
        /* Returns the logged path in factoized form */

        // Iterates throught the path, adding the length of each sub-list before the contained operation
        String factorized = "";
        for (ArrayList<Character> chain : path) {

            // Only add the length of sub-lists bigger than one, to avoid a 1 before singleton operations
            if (chain.size() > 1) {
                factorized = factorized + String.valueOf(chain.size()) + chain.get(0) + ' ';
            } else {
                factorized = factorized + chain.get(0) + ' ';
            }
            
        }
        return factorized;
    }
}


enum Move {
    NONE, FORWARD, LEFT, RIGHT
}