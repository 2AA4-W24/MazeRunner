package ca.mcmaster.se2aa4.mazerunner;
import java.util.ArrayList;

public class MovementLogger {
    private ArrayList<ArrayList<Character>> path = new ArrayList<ArrayList<Character>>();
    private Move last_move = Move.NONE;

    public void forward() {
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
        if (last_move == Move.LEFT) {
            path.getLast().add('L');
        } else {
            ArrayList<Character> temp_list = new ArrayList<Character>();
            temp_list.add('L');
            path.add(temp_list);
            last_move = Move.LEFT;
        }
    }

    public String getCanonical() {
        String canonical = "";
        for (ArrayList<Character> chain : path) {
            for (Character move : chain) {
                canonical = canonical + move;
            }
            canonical = canonical + ' ';
        }
        return canonical;
    }
}


enum Move {
    NONE, FORWARD, LEFT, RIGHT
}