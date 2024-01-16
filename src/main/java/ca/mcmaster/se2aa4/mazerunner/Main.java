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
            BufferedReader reader = new BufferedReader(new FileReader(config.input_maze()));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.println("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.println("PASS ");
                    }
                }
                System.out.println(System.lineSeparator());
            }
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
