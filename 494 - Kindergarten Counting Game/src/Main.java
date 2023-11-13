import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {

    public static void main(String[] args) {
        //Setup logger for easier debugging
        Logger logger = Logger.getLogger("494");
        logger.setLevel(Level.OFF);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);
        logger.addHandler(consoleHandler);
        //Start code
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine(); //the line of input
            logger.fine("Input Line: " + line);
            String regex = "[^A-Za-z]+"; //filters out all non-alpha characters
            String filteredLine = line.replaceAll(regex," "); //replaces all non-alphas with a space
            logger.fine("Filtered Line: " + filteredLine);
            String[] words = filteredLine.trim().split(" "); //split the filteredLine by the spaces in the string
            logger.fine(Arrays.toString(words));
            System.out.println(words.length);
        }
    }
}
