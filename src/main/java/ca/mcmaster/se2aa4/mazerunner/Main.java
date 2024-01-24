package ca.mcmaster.se2aa4.mazerunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.lang.module.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        try {
            Configuration config = configure(args);
            logger.info("**** Reading the maze from file " + config.input_maze());
            MazeReader reader = new MazeReader(config.input_maze());
            Maze maze = reader.readMaze();

            if (config.has_path()) {
                logger.info("**** Checking path");
                System.out.println(config.input_path());
                PathChecker checker = new PathChecker(maze);
                boolean check_result = checker.checkPath(config.input_path());

                if (check_result) {
                    System.out.println("Your input solution is correct!");
                } else {
                    System.out.println("Your input solution is incorrect.");
                }

            } else {
                logger.info("**** Computing path");
                MazeSolver solver = new RightHandAlgo();
                String solution = solver.solveMaze(maze);
                System.out.println("Solution: " + solution);
            }

        } catch(IOException | ParseException | EntranceException | ExitException e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException{
        Options options = new Options();
        options.addOption("i", true, "flag for maze file location");
        options.addOption("p", true, "flag for path checking");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String maze  = cmd.getOptionValue("i");
        boolean has_path = cmd.hasOption("p");
        String path = cmd.getOptionValue("p");
        return new Configuration(maze, path, has_path);
    }

    private record Configuration(String input_maze, String input_path, boolean has_path) {}
}
