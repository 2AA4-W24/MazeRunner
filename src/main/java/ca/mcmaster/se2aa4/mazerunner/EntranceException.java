package ca.mcmaster.se2aa4.mazerunner;

public class EntranceException extends Exception {
    // Thrown when there's difficulty locating maze entrances
    public EntranceException(String message) {
        super(message);
    }
}
