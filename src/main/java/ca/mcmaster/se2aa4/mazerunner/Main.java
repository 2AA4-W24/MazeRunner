package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
            BufferedReader read = new BufferedReader(new FileReader(config.input_maze()));
            String line;
            while ((line = read.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
            MazeReader reader = new MazeReader(config.input_maze());
            Maze maze = reader.readMaze();
            MazeSolver solver = new RightHandAlgo();
            String solution = solver.solveMaze(maze);
            System.out.println("Solution: " + solution);
        } catch(Exception e) {
            logger.error(e.getMessage());
            System.exit(1);
        }
        logger.info("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    private static Configuration configure(String[] args) throws ParseException{
        Options options = new Options();
        options.addOption("i", true, "i flag");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String maze  = cmd.getOptionValue("i");
        return new Configuration(maze);
    }

    private record Configuration(String input_maze) {}
}
