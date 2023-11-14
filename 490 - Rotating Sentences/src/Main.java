import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();//used to store all of our scanner's input in one string

        //collects all the input into a single string and exits the loop when there's no more input
        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.isEmpty())
                break;

            stringBuilder.append(input).append("\n"); //adds the line to our StringBuilder
        }

        sc.close();//closes the scanner
        String input = stringBuilder.toString(); //a string representing all of our input
        String[] sentences = input.split("\n"); //split our input string by the newline all Strings will be 100 characters or less
        StringBuilder sb = new StringBuilder(); //StringBuilder to hold our rotated sentences

        Collections.reverse(Arrays.asList(sentences));//reverse the order of our array to get the last characters first
            for (int j = 0; j < 100; j++) { //loops through the max size of a given sentence
                for (String sentence : sentences) { //loops through to the total number of sentences
                    sb.append(main.getCharAtIndex(sentence, j)); //add the character for the specified index
                }
                sb.append("\n");//add a new line after the line length reaches the total number of sentences
            }
            System.out.println(sb.toString().trim());//trim of extra white space to avoid presentation error
    }


    /**
     * Retrieves the character at the specified index in the given sentence.
     * If the index is out of bounds or the character is not found in the sentence,
     * it returns a space character (' ').
     *
     * @param sentence The input sentence to retrieve the character from.
     * @param index    The index of the character to retrieve.
     * @return The character at the specified index or a space character if the index is out of bounds or the character is not found.
     */
    char getCharAtIndex(String sentence, int index) {
        try {
            int sentenceIndex = sentence.indexOf(sentence.charAt(index));

            if (sentenceIndex != -1) {
                return sentence.charAt(sentenceIndex);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return ' ';
        }
        return ' ';
    }
}
