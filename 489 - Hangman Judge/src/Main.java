import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Problem {@link <a href="https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=94&page=show_problem&problem=430">489 - Hangman Judge</a>}
 * Status: Accepted
 * Run Time: 2.520
 */
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(); //stores our printable data rather than printing each line
        while (true) {
            int round = Integer.parseInt(sc.nextLine()); //round of the game

            if (round == -1)//end the program
                break;

            String answer = sc.nextLine();//the correct answer
            String guess = sc.nextLine();//the guessed sequence
            int result = main.compareAttempts(answer, guess);//1 = win -1 = lose 0 = fail

            sb.append("Round ").append(round).append('\n');//required for presentation
            switch (result) {//prints out the result of the round
                case 1:
                    sb.append("You win.");
                    break;
                case -1:
                    sb.append("You lose.");
                    break;
                case 0:
                    sb.append("You chickened out.");
                    break;
            }
            System.out.println(sb);//print out the content of our StringBuilder
            sb.setLength(0);//reset the StringBuilder for next round
        }
        sc.close();
    }

    /**
     * Compares the guessed word with the correct answer and determines the result.
     *
     * @param answer The correct answer word.
     * @param guess  The guessed word.
     * @return 1 if the guess is correct and matches the answer, -1 if the guess is incorrect and exceeds the allowed attempts, 0 otherwise.
     */
    int compareAttempts(String answer, String guess) {
        int matches = 0; //the amount of character matches found
        int incorrectAttempts = 0; //the amount of incorrect guesses in the sequence
        Set<Character> usedCharacters = new HashSet<>();//stores used characters to prevent running on duplicate characters
        guesses:for (char guessedCharacter : guess.toCharArray()) {//named loop to return to / break out of loops through all characters in guess sequence
            if (usedCharacters.add(guessedCharacter)) {//true if character has never been used and adds it to the set
                for (char correctCharacter : answer.toCharArray()) {//loops through all characters in the answer
                    if (answer.contains(String.valueOf(guessedCharacter))) {//checks if the guessed character exists in the answer string
                        if (guessedCharacter == correctCharacter) {//checks for matching characters
                            matches++;
                            if (matches == answer.length()) //ends the loop if the amount of matches equals our answers length
                                break guesses;
                        }
                    } else {//increments our incorrect guesses if the character is not in the answer
                        incorrectAttempts++;
                        continue guesses;//continue our guessed character sequence to avoid checking the string for the character multiple times
                    }
                }
            }
        }
        if (matches == answer.length() && incorrectAttempts < 7) {//if the match count equals the length of the answer and the attempts is less than 7 win
            return 1; // win
        } else if (incorrectAttempts >= 7) { //if the attempts count is more than 7 lose
            return -1; // lose
        } else { //fail not enough characters guessed
            return 0; // fail
        }
    }
}
