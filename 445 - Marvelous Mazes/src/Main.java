import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Draws a mazed based on input given
 * Valid characters: A-Z, *, space
 * The number before a character determines how many times to repeat that character
 * if there's multiple digits in the number it's the sum of all of them
 * <p>
 * lowercase 'b' represents a space in maze
 * rows are separated by ! or end of line
 * <p>
 * new mazes are separated by a new line
 * <p>
 * no limit on rows or mazes but row has a character limit of 132
 */
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //reads the user input
        StringBuilder output = new StringBuilder(); //stores the output

        while (true) {
            String line = reader.readLine();

            if (line == null)
                break;

            int printCount = 0; //stores the amount of times to print a given character
            for (int i = 0; i < line.length(); i++) { //loops through the length of the line
                char c = line.charAt(i); //a given character in the line

                if (Character.isDigit(c)) { //checks if the character is a digit
                    printCount += Integer.parseInt(String.valueOf(c)); //increases the print count by the value of the digit
                } else {
                    if (c == 'b') {//checks if the character is 'b'
                        c = ' '; //converts 'b' into a space
                    } else if (c == '!') { //checks for '!' to start a new row
                        output.append('\n');
                    }
                    for (int j = 0; j < printCount; j++) {
                        output.append(c); //prints the given character the summed amount of times
                    }
                    printCount = 0; //resets the print count for the next line
                }
            }
            System.out.println(output);
            output.setLength(0); //resets the output for the next row
        }
    }
}
